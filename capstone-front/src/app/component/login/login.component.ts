import { HttpErrorResponse, HttpResponse, HttpResponseBase } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuth } from 'src/app/object/user-auth';
import { UserAuthRequest } from 'src/app/object/user-auth-request';
import { AuthService } from 'src/app/service/auth.service';

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

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.login(this.userAuthRequest).subscribe(
      {
        next: (response: HttpResponse<UserAuth>) => {
          console.log(response.headers.get('authorization')); // DEBUG
          let jwt: string = response.headers.get('authorization') ?? '';
          localStorage.setItem("jwt", jwt);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
        }
      }
    );
  }

  logout(): void {
    console.log("logout called.");
    this.authService.logout();
  }

}
