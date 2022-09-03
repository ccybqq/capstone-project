import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuth } from 'src/app/object/user-auth';
import { AuthService } from 'src/app/service/auth.service';
import { FormatterService } from 'src/app/service/formatter.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public userAuth: UserAuth = {
    password: '',
    userEntity: {
      id: null,
      email: '',
      firstName: '',
      lastName: '',
      dateOfBirth: '',
      age: 0,
      gender: '',
      weight: 0,
      bloodGroup: '',
      contactNumber: '',
      state: '',
      area: '',
      pinCode: 0
    }
  };

  constructor(protected authService: AuthService, protected router: Router, protected formatter: FormatterService) { }

  ngOnInit(): void {
  }

  register(): void {
    this.userAuth.userEntity.gender = this.formatter.frontToBack(this.userAuth.userEntity.gender!, this.formatter.gender);
    this.userAuth.userEntity.bloodGroup = this.formatter.frontToBack(this.userAuth.userEntity.bloodGroup!, this.formatter.bloodGroup);
    this.userAuth.userEntity.state = this.formatter.frontToBack(this.userAuth.userEntity.state!, this.formatter.state);
    this.userAuth.userEntity.area = this.formatter.frontToBack(this.userAuth.userEntity.area!, this.formatter.area);

    console.log("Sending registration details...");
    console.log(this.userAuth);
    this.authService.register(this.userAuth).subscribe(
      {
        next: (response: HttpResponse<UserAuth>) => {
          console.log("User registered.");
          this.router.navigate(['login']);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.headers.get('Message'));
        }
      }
    )
  }

}
