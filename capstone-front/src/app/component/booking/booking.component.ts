import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingEntity } from 'src/app/object/booking-entity';
import { UserEntity } from 'src/app/object/user-entity';
import { BookingService } from 'src/app/service/booking.service';
import { FormatterService } from 'src/app/service/formatter.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  
  bookingEntity: BookingEntity = {
    id: null,
    email: '',
    city: '',
    hospital: '',
    date: null,
    bookingSlot: ''
  }
  public isBookingSuccessful: boolean = false;

  constructor(
    private userService: UserService,
    private bookingService: BookingService,
    protected formatter: FormatterService,
    protected router: Router) { }

  ngOnInit(): void {
    this.bookingEntity.email = localStorage.getItem('username')!;
  }

  book(): void {
    let newBooking: BookingEntity = {
      id: null,
      email: this.bookingEntity.email,
      city: this.bookingEntity.city,
      hospital: this.bookingEntity.hospital,
      date: this.bookingEntity.date,
      bookingSlot: this.formatter.frontToBack(this.bookingEntity.bookingSlot, this.formatter.bookingSlot)
    }
    this.bookingService.book(newBooking).subscribe(
      {
        next: () => this.isBookingSuccessful = true,
        error: (error: HttpErrorResponse) => {
          if (error.headers.has('Message')) alert("Booking slot unavailable. Please select another time or hospital.");
          console.log(error);
        }
      }
    )
  }
}
