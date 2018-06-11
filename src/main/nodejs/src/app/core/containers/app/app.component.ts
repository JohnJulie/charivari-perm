import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  styleUrls: ['./app.component.scss'],
  template: `
    <app-sidenav></app-sidenav>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  title = 'Charivalaperm';
}
