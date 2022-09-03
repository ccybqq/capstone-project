import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Area } from 'src/app/object/area';
import { BloodGroup } from 'src/app/object/blood-group';
import { Gender } from 'src/app/object/gender';
import { State } from 'src/app/object/state';
import { UserEntity } from 'src/app/object/user-entity';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  public userEntity!: UserEntity | null;

  constructor(private userService: UserService, protected router: Router) { }

  ngOnInit(): void {
    let username: string | null = localStorage.getItem('username');
    if (username !== null) this.getUser(username);
    else this.router.navigate(['/login']);
  }

  public getUser(email: string): void {
    this.userService.getUserByEmail(email).subscribe(
      {
        next: (response: HttpResponse<UserEntity>) => {
          this.userEntity = response.body;
          var x = <Gender> this.userEntity!.gender;
          var y = Gender.MALE;
          var z = x === y;
          console.log(z);
          console.log(<Gender> this.userEntity!.gender);
          this.userEntity!.gender = <Gender> this.userEntity!.gender;
          this.userEntity!.bloodGroup = <BloodGroup> this.userEntity!.bloodGroup;
          this.userEntity!.state = <State> this.userEntity!.state;
          this.userEntity!.area = <Area> this.userEntity!.area;
          console.log(this.userEntity);
        },
        error: (e: HttpErrorResponse) => {
          if (e.status == 400) alert(e.headers.get("message"));
          else console.log(e);
        }
      }
    );
  }

}
