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

  isAdmin: boolean = false;

  constructor(protected authService: AuthService, protected router: Router, protected formatter: FormatterService) { }

  ngOnInit(): void {
  }

  register(): void {
    let newUser: UserAuth = {
      password: this.userAuth.password,
      userEntity: {
        id: this.userAuth.userEntity.id,
        email: this.userAuth.userEntity.email,
        firstName: this.userAuth.userEntity.firstName,
        lastName: this.userAuth.userEntity.lastName,
        dateOfBirth: this.userAuth.userEntity.dateOfBirth,
        age: this.userAuth.userEntity.age,
        gender: this.formatter.frontToBack(this.userAuth.userEntity.gender!, this.formatter.gender),
        weight: this.userAuth.userEntity.weight,
        bloodGroup: this.formatter.frontToBack(this.userAuth.userEntity.bloodGroup!, this.formatter.bloodGroup),
        contactNumber: this.userAuth.userEntity.contactNumber,
        state: this.formatter.frontToBack(this.userAuth.userEntity.state!, this.formatter.state),
        area: this.formatter.frontToBack(this.userAuth.userEntity.area!, this.formatter.area),
        pinCode: this.userAuth.userEntity.pinCode
      }
    };

    this.authService.register(newUser).subscribe(
      {
        next: (response: HttpResponse<UserAuth>) => {
          if (this.isAdmin) {
            this.authService.makeAdmin(response.body!.userEntity.id!).subscribe(
              {
                next: () => console.log("admin"),
                error: (e: HttpErrorResponse) => console.log(e)
              }
            );
          }
          alert("User created successfully. Please log in.");
          this.router.navigate(['login']);
        },
        error: (error: HttpErrorResponse) => {
          alert("Email already in use.")
          console.log(error.headers.get('Message'));
        }
      }
    )
  }

}
