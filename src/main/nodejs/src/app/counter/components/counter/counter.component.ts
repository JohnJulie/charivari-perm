import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FamilyModel } from '../../../shared/models/family.model';

@Component({
  selector: 'app-counter',
  templateUrl: './counter.component.html',
  styleUrls: ['./counter.component.scss']
})
export class CounterComponent implements OnInit, OnChanges {

  @Input() families: FamilyModel[];
  @Input() familyCount: any[];
  public colNumber: number;

  constructor() { }

  ngOnInit() {
    this.colNumber = 5;
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log('changer:', changes);
    console.log('ngOnChanges families:', this.families);
    console.log('ngOnChanges familyCount:', this.familyCount);
  }

  onResize(event) {
    const element = event.target.innerWidth;

    if (element < 950) {
      this.colNumber = 4;
    }

    if (element > 950) {
      this.colNumber = 5;
    }

    if (element < 750) {
      this.colNumber = 2;
    }
  }

}
