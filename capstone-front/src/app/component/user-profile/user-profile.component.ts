import { Component, OnInit } from '@angular/core';
import { UserEntity } from 'src/app/objects/user-entity';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  public userEntity!: UserEntity;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUser("a@b.c");
  }

  public getUser(email: string): void {
    this.userService.getUserByEmail(email).subscribe(
      {
        next: (response: UserEntity) => {
          this.userEntity = response;
        },
        error: () => {
          alert("error");
        }
      }
    );
  }

}
