import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserAuth } from '../object/user-auth';
import { UserAuthRequest } from '../object/user-auth-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient, private router: Router) { }

  login(userAuthRequest: UserAuthRequest): Observable<HttpResponse<UserAuth>> {
    return this.http.post<UserAuth>(this.apiServerUrl + '/login', userAuthRequest, { observe: 'response' });
  }

  register(userAuth: UserAuth): Observable<HttpResponse<UserAuth>> {
    return this.http.post<UserAuth>(this.apiServerUrl + '/api/auth', userAuth, { observe: 'response' });
  }

  checkAdmin(email: string): Observable<boolean> {
    let params = new HttpParams();
    params = params.set('email', email)
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', localStorage.getItem('jwt') ?? '')
    return this.http.get<boolean>(this.apiServerUrl + '/api/auth/isAdmin', { params, headers });
  }

  isAdmin(): boolean {
    return localStorage.getItem('isAdmin') === 'true';
  }

  isLoggedIn() {
    return localStorage.getItem('username') !== null;
  }

  logout() {
    localStorage.removeItem('username');
    localStorage.removeItem('isAdmin');
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }
}
