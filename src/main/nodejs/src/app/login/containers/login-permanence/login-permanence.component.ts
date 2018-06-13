import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/account/account.service';
import { StorageService } from '../../../shared/services/storage/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-permanence',
  templateUrl: './login-permanence.component.html',
  styleUrls: ['./login-permanence.component.scss']
})
export class LoginPermanenceComponent implements OnInit {

  public isLogged: boolean;

  constructor(
    private accountService: AccountService,
    private storageService: StorageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.isLogged = null;
    console.log('session', this.storageService.read('cpu'));
  }

  onConnexion(event) {
    this.accountService.connexion(event).subscribe(
      account => {
        if (account) {
          this.storageService.save('cpu', account);
          this.isLogged = true;
          this.router.navigate(['permanence']);
        } else {
          this.isLogged = false;
        }
      }, error => {
        this.isLogged = false;
        console.log('error on connexion:', error);
      }
    );
  }

}
