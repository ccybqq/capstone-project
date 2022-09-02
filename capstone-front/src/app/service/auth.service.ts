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

  constructor(private http: HttpClient, private router: Router) { }

  login(userAuthRequest: UserAuthRequest): Observable<HttpResponse<UserAuth>> {
    return this.http.post('http://localhost:8080/login', userAuthRequest, { observe: 'response' });
  }

  // TODO: register()

  isLoggedIn() {
    return localStorage.getItem('username') !== null;
  }

  logout() {
    localStorage.removeItem('username');
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }
}
