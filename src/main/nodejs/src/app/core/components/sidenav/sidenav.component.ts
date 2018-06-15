import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { StorageService } from '../../../shared/services/storage/storage.service';
import { AccountType } from '../../../shared/models/account-type.model';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  public isLogged: boolean;
  public isAdmin: boolean;

  constructor(
    private router: Router,
    private storageService: StorageService
  ) { }

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        if (this.storageService.read('cpu')) {
          this.isLogged = true;
          this.isAdmin = this.storageService.read('cpu').type === AccountType.admin;
        } else {
          this.isLogged = false;
          this.isAdmin = false;
        }
      }
    });
  }

  logout() {
    this.storageService.clear();
    this.router.navigate(['']);
  }

}
