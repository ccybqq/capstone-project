import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BloodRegistryEntity } from 'src/app/object/blood-registry-entity';
import { ReviewEntity } from 'src/app/object/review-entity';
import { AuthService } from 'src/app/service/auth.service';
import { BloodRegistryService } from 'src/app/service/blood-registry.service';
import { FormatterService } from 'src/app/service/formatter.service';
import { ReviewService } from 'src/app/service/review.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  hospital: string = '';
  city: string = '';
  message: string = '';

  private numOfReviews: number = 3;
  reviewArr: ReviewEntity[] = [];

  requiredArr: BloodRegistryEntity[] = [];

  constructor(
    private bloodRegistryService: BloodRegistryService,
    private reviewService: ReviewService,
    protected authService: AuthService,
    protected formatter: FormatterService,
    protected router: Router
  ) { }

  ngOnInit(): void {
    this.reviewService.getRandom(this.numOfReviews).subscribe(
      {
        next: (response: HttpResponse<ReviewEntity[]>) => this.reviewArr = response.body!,
        error: (error: HttpErrorResponse) => console.log(error.headers.get('Message'))
      }
    )

    this.bloodRegistryService.getAllRequired().subscribe(
      {
        next: (response: HttpResponse<BloodRegistryEntity[]>) => {
          this.requiredArr = response.body!;
        },
        error: (error: HttpErrorResponse) => console.log(error.headers.get('Message'))
      }
    )
  }

  post() {
    let newReview: ReviewEntity = {
      hospital: this.hospital,
      city: this.city,
      message: this.message
    };
    this.reviewService.add(newReview).subscribe(
      {
        next: (response: HttpResponse<ReviewEntity>) => {
          this.reviewArr.unshift(response.body!);
          this.reviewArr.splice(-1);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.headers.get('Message'));
        }
      }
    )
  }

}
