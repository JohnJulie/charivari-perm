import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Rx";

@Injectable()
export class PermanenceService {

  constructor() { }

    getCurrentPermanence() {
        return Observable.of({
            'id': 0,
            'startDate': '2017-10-03T07:45:00',
            'endStart': '2017-10-03T10:45:00',
            'status': 'NOT_CONFIRMED',
            'family': {
                'id': 0,
                'label': 'William, Julie & Adrien'
             }
        });
    }
}
