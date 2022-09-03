import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Area } from 'src/app/object/area';
import { BloodGroup } from 'src/app/object/blood-group';
import { Gender } from 'src/app/object/gender';
import { State } from 'src/app/object/state';
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
      email: null,
      firstName: null,
      lastName: null,
      dateOfBirth: null,
      age: null,
      gender: null,
      weight: null,
      bloodGroup: null,
      contactNumber: null,
      state: null,
      area: null,
      postalCode: null
    }
  };
  gender = Gender;
  bloodGroup = BloodGroup;
  state = State;
  area = Area;
  object = Object;

  constructor(protected authService: AuthService, protected router: Router, private formatter: FormatterService) { }

  ngOnInit(): void {
  }

  register(): void {
    this.userAuth.userEntity.gender = <Gender> this.formatter.valueToKey(<string> this.userAuth.userEntity.gender!, Gender);
    this.userAuth.userEntity.bloodGroup = <BloodGroup> this.formatter.valueToKey(<string> this.userAuth.userEntity.bloodGroup!, BloodGroup);
    this.userAuth.userEntity.state = <State> this.formatter.valueToKey(<string> this.userAuth.userEntity.state!, State);
    this.userAuth.userEntity.area = <Area> this.formatter.valueToKey(<string> this.userAuth.userEntity.area!, Area);

    console.log("Sending registration details...");
    console.log(this.userAuth);
    this.authService.register(this.userAuth).subscribe(
      {
        next: (response: HttpResponse<UserAuth>) => {
          console.log("User registered.");
          this.router.navigate(['login']);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
        }
      }
    )
  }

}
