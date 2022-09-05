import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookingEntity } from '../object/booking-entity';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  private apiServerUrl: string = 'http://localhost:8080/api/booking'

  constructor(private http: HttpClient) { }

  book(bookingEntity: BookingEntity): Observable<HttpResponse<BookingEntity>> {
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', localStorage.getItem('jwt') ?? '')
    return this.http.post<BookingEntity>(this.apiServerUrl, bookingEntity, { observe: 'response' })
  }

  isSlotAvailable(city: string, hospital: string, date: string, bookingSlot: string): Observable<HttpResponse<boolean>> {
    let params = new HttpParams();
    params = params.set('city', city);
    params = params.set('hospital', hospital);
    params = params.set('date', date);
    params = params.set('bookingSlot', bookingSlot);
    return this.http.get<boolean>(this.apiServerUrl, { observe: 'response', params})
  }
}
