import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-close-month',
  templateUrl: './close-month.component.html',
  styleUrls: ['./close-month.component.scss']
})
export class CloseMonthComponent implements OnInit, OnChanges {

  @Input() monthToClose;
  @Output() close: EventEmitter<any> = new EventEmitter();
  public selectedMonth;

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges() {
    console.log('monthToClose:', this.monthToClose);
  }

  closeMonth() {
    const month = this.selectedMonth;
    this.close.emit(month);
    this.selectedMonth = null;
  }

}
