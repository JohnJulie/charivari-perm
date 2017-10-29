import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class PermanenceService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

    getCurrentPermanence() {
      const headers = new HttpHeaders({
        'Content-type': 'application/json'
      });
      return this.http.get(this.apiUrl + 'permanence/now', { headers: headers });
        // return Observable.of({
        //     'id': 0,
        //     'startDate': '2017-10-03T07:45:00',
        //     'endDate': '2017-10-03T10:45:00',
        //     'status': 'NOT_CONFIRMED',
        //     'family': {
        //       'id': 0,
        //       'label': 'William, Julie & Adrien',
        //       'urlImage': 'william-logo.jpg'
        //      }
        // });
    }
}
