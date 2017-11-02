import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class FamilyService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getFamilies() {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get(this.apiUrl + 'families', { headers: headers });
  }

}
