import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PermanenceModel } from '../../models/permanence.model';

@Injectable()
export class PermanenceService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

    getCurrentPermanence(): Observable<Array<PermanenceModel>> {
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      return this.http.get(this.apiUrl + 'permanence/now', { headers: headers });
    }

    getPermanence(id): Observable<PermanenceModel> {
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      return this.http.get(this.apiUrl + 'permanence/' + id, { headers: headers });
    }

    updatePermanence(permanence) {
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      console.log('permanence 555:', permanence);
      console.log(this.apiUrl + 'permanence/update');
      return this.http.put(this.apiUrl + 'permanence/update', permanence, { headers: headers });
    }

    getWeekPermanence(fromDate, toDate): Observable<Array<PermanenceModel>> {
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      return this.http.get(this.apiUrl + 'permanence/from/' + fromDate + '/to/' + toDate, { headers: headers });
    }
}
