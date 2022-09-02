import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserEntity } from '../objects/user-entity';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl = 'http://localhost:8080/api/user'

  constructor(private http: HttpClient) { }

  public getUserByEmail(email: string): Observable<HttpResponse<UserEntity>> {
    let params = new HttpParams();
    params = params.set('email', email)
    let response = this.http.get<UserEntity>(this.apiServerUrl, { observe: 'response', params });
    return response;
  }
}
