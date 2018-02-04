import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class CurrentStateService {

  private _state = new BehaviorSubject<any>({});
  state = this._state.asObservable();

  constructor() { }

  updateState(state) {
    this._state.next(state);
  }
}
