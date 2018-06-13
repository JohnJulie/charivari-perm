import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from '../../../shared/services/storage/storage.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  constructor(
    private router: Router,
    private storageService: StorageService
  ) { }

  ngOnInit() {
  }

  logout() {
    this.storageService.clear();
    this.router.navigate(['']);
  }

}
