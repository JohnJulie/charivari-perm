/**
 * Created by juliepel on 14/06/2018.
 */
import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { StorageService } from '../../shared/services/storage/storage.service';
import { AccountType } from '../../shared/models/account-type.model';

@Injectable()
export class AccountGuard implements CanActivate {

  constructor(
    private router: Router,
    private storageService: StorageService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.storageService.read('cpu') && this.storageService.read('cpu').type === AccountType.admin) {
      // logged in so return true
      return true;
    }

    return false;
  }
}
