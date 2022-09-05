import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReviewEntity } from '../object/review-entity';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private apiServerUrl: string = 'http://localhost:8080/api/review';

  constructor(private http: HttpClient) { }

  add(reviewEntity: ReviewEntity): Observable<HttpResponse<ReviewEntity>> {
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', localStorage.getItem('jwt') ?? '')
    return this.http.post<ReviewEntity>(this.apiServerUrl, reviewEntity, { observe: 'response', headers });
  }

  getRandom(number: number): Observable<HttpResponse<ReviewEntity[]>> {
    return this.http.get<ReviewEntity[]>(this.apiServerUrl + '/' + number.toString(), { observe: 'response' });
  }
}
