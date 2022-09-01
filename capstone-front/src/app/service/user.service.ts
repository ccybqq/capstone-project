import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserEntity } from '../objects/user-entity';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl = 'localhost:8080/api/user'

  constructor(private http: HttpClient) { }

  public getUserByEmail(email: string): Observable<UserEntity> {
    let params = new HttpParams();
    params = params.set('email', email)
    return this.http.get<UserEntity>('http://localhost:8080/api/user', { params });
  }
}
