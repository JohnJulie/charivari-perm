import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FamilyModel } from '../../models/family.model';
import { Observable } from 'rxjs';

@Injectable()
export class FamilyService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getFamilies(): Observable<FamilyModel[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get<FamilyModel[]>(this.apiUrl + 'families', { headers: headers });
  }

}
