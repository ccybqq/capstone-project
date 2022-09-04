import { HttpErrorResponse, HttpResponse, HttpResponseBase } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuth } from 'src/app/object/user-auth';
import { UserAuthRequest } from 'src/app/object/user-auth-request';
import { UserEntity } from 'src/app/object/user-entity';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userAuthRequest:  UserAuthRequest = {
    username: '',
    password: ''
  }
  hasFailed: boolean = false;

  constructor(protected authService: AuthService, private userService: UserService, protected router: Router) { }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.login(this.userAuthRequest).subscribe(
      {
        next: (response: HttpResponse<UserAuth>) => {
          let jwt: string = response.headers.get('authorization') ?? '';
          localStorage.setItem("jwt", jwt);

          this.setAdmin(this.userAuthRequest.username);
          this.setProfileDetails(this.userAuthRequest.username);
          this.router.navigate(['home']);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
          this.hasFailed = true
        }
      }
    );
  }

  logout(): void {
    this.authService.logout();
  }

  setProfileDetails(email: string) {
    this.userService.getUserByEmail(email).subscribe(
      {
        next: (response: HttpResponse<UserEntity>) => {
          let user: UserEntity = response.body!;
          localStorage.setItem("username", user.email);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.headers.get('Message'));
        }
      }
    )
  }

  setAdmin(email: string) {
    this.authService.checkAdmin(email).subscribe(
      {
        next: (response: boolean) => {
          localStorage.setItem('isAdmin', String(response));
        }
      }
    );
    
  }

  isAdmin(): boolean {
    return localStorage.getItem('isAdmin') === 'true';
  }

}
