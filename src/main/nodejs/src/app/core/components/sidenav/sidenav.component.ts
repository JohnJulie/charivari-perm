import { Component, OnInit } from '@angular/core';
import { CurrentStateService } from '../../../services/shared-data/current-state.service';

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

  public setActivateTab (url: string, index: number) {
    this.activateMenu = index;
  }

}
