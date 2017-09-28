import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  public activateMenu: number;
    
  constructor() { }

  ngOnInit() {
    this.activateMenu = 0;
  }
    
  public setActivateTab (sidenav: any, index: number) {
    this.activateMenu = index;
    this.toggleSidenav(sidenav);
  }
    
  public toggleSidenav(nav: any) {
    if (nav.opened) {
      nav.close();
    } else {
      nav.open();  
    }
  }

}
