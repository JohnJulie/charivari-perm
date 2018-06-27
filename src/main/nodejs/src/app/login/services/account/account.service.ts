import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Account } from '../../models/account.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  connexion(authent: any): Observable<Account> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
    });
    return this.http.post<Account>(this.apiUrl + 'connexion', authent, { headers: headers });
  }
}
