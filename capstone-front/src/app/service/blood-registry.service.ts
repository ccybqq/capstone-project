import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodRegistryEntity } from '../object/blood-registry-entity';

@Injectable({
  providedIn: 'root'
})
export class BloodRegistryService {
  private apiServerUrl = 'http://localhost:8080/api/registry';

  constructor(private http: HttpClient) { }

  public getBloodRegistryEntity(bloodRegistryEntity: BloodRegistryEntity): Observable<HttpResponse<BloodRegistryEntity>> {
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', localStorage.getItem('jwt') ?? '')
    return this.http.post<BloodRegistryEntity>(this.apiServerUrl + "/get", bloodRegistryEntity, { observe: 'response', headers});
  }

  public update(id: number, available: boolean, required: boolean): Observable<HttpResponse<BloodRegistryEntity>> {
    let params = new HttpParams();
    params = params.set('id', id);
    params = params.set('available', available);
    params = params.set('required', required);
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', localStorage.getItem('jwt') ?? '')
    return this.http.put<BloodRegistryEntity>(this.apiServerUrl, null, { observe: 'response', headers, params});
  }

  public add(bloodRegistryEntity: BloodRegistryEntity): Observable<HttpResponse<BloodRegistryEntity>> {
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', localStorage.getItem('jwt') ?? '')
    return this.http.post<BloodRegistryEntity>(this.apiServerUrl + "/add", bloodRegistryEntity, { observe: 'response', headers});
  }

  public delete(id: number): Observable<HttpResponse<BloodRegistryEntity>> {
    let params = new HttpParams();
    params = params.set('id', id);
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', localStorage.getItem('jwt') ?? '')
    return this.http.delete<BloodRegistryEntity>(this.apiServerUrl, { observe: 'response', headers, params});
  }
}
