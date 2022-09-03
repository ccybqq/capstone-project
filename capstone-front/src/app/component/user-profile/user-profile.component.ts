import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserEntity } from 'src/app/object/user-entity';
import { FormatterService } from 'src/app/service/formatter.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  public userEntity!: UserEntity | null;

  constructor(private userService: UserService, protected router: Router, protected formatter: FormatterService) { }

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
          console.log(this.userEntity);
          this.userEntity!.gender = this.formatter.backToFront(this.userEntity!.gender, this.formatter.gender);
          this.userEntity!.bloodGroup = this.formatter.backToFront(this.userEntity!.bloodGroup, this.formatter.bloodGroup);
          this.userEntity!.state = this.formatter.backToFront(this.userEntity!.state, this.formatter.state);
          this.userEntity!.area = this.formatter.backToFront(this.userEntity!.area, this.formatter.area);
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
