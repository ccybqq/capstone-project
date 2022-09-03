import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserAuth } from '../object/user-auth';
import { UserAuthRequest } from '../object/user-auth-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient, private router: Router) { }

  login(userAuthRequest: UserAuthRequest): Observable<HttpResponse<UserAuth>> {
    return this.http.post<UserAuth>(this.baseUrl + '/login', userAuthRequest, { observe: 'response' });
  }

  register(userAuth: UserAuth): Observable<HttpResponse<UserAuth>> {
    return this.http.post<UserAuth>(this.baseUrl + '/api/auth', userAuth, { observe: 'response' });
  }

  isLoggedIn() {
    return localStorage.getItem('username') !== null;
  }

  logout() {
    localStorage.removeItem('username');
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }
}
