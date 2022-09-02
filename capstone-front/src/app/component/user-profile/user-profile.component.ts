import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Gender } from 'src/app/objects/gender';
import { UserEntity } from 'src/app/objects/user-entity';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  public userEntity!: UserEntity | null;
  public gender!: Gender;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUser("jean.smith@mymail.com");
  }

  public getUser(email: string): void {
    this.userService.getUserByEmail(email).subscribe(
      {
        next: (response: HttpResponse<UserEntity>) => {
          this.userEntity = response.body;
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
