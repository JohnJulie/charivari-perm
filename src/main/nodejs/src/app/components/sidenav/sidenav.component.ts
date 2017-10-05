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
    
  public setActivateTab (index: number) {
    this.activateMenu = index;
  }

}
