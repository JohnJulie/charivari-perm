import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PermanenceModel } from '../../models/permanence.model';

@Injectable()
export class PermanenceService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getCurrentPermanence(): Observable<PermanenceModel[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get<PermanenceModel[]>(this.apiUrl + '/permanence/now', { headers: headers });
  }

  getPermanence(id): Observable<PermanenceModel> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get<PermanenceModel>(this.apiUrl + '/permanence/' + id, { headers: headers });
  }

  updatePermanence(permanence) {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.put(this.apiUrl + '/permanence/update', permanence, { headers: headers });
  }

  getWeekPermanence(fromDate, toDate): Observable<PermanenceModel[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get<PermanenceModel[]>(this.apiUrl + '/permanence/from/' + fromDate + '/to/' + toDate, { headers: headers });
  }

  getPermanenceBySlot(date, slot) {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get(this.apiUrl + '/permanence/date/' + date + '/slot/' + slot, { headers: headers });
  }

  getReplacements(nobodyId): Observable<PermanenceModel[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get<PermanenceModel[]>(this.apiUrl + '/permanence/replacements/' + nobodyId, { headers: headers });
  }

  getValidations(): Observable<PermanenceModel[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get<PermanenceModel[]>(this.apiUrl + '/permanence/notvalidate', { headers: headers });
  }

  setFlyingPerm(nobodyId, familyId, startDate) {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.post(this.apiUrl + '/permanence/flying/' + nobodyId + '/family/' + familyId + '/at/' + startDate, { headers: headers });
  }

  validateMonth(date) {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.put(this.apiUrl + '/permanence/close', date, { headers: headers });
  }

  getMonthToValidate() {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get(this.apiUrl + '/permanence/tovalidate', { headers: headers });
  }

  getCountPermByFamily(familyId) {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.get(this.apiUrl + '/count/' + familyId, { headers: headers });
  }
}
