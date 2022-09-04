import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
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
    console.log(headers.get('Authorization'));
    return this.http.post<BloodRegistryEntity>(this.apiServerUrl + "/get", bloodRegistryEntity, { observe: 'response', headers});
  }
}
