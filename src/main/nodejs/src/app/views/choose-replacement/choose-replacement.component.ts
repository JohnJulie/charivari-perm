import { Component, OnInit } from '@angular/core';
import { FamilyModel } from '../../models/family.model';
import { FamilyService } from '../../services/family/family.service';

@Component({
  selector: 'app-choose-replacement',
  templateUrl: './choose-replacement.component.html',
  styleUrls: ['./choose-replacement.component.scss']
})
export class ChooseReplacementComponent implements OnInit {

  public families: Array<FamilyModel>;

  constructor(private familyService: FamilyService) { }

  ngOnInit() {
    this.familyService.getFamilies().subscribe(
      (families: Array<FamilyModel>) => {
        this.families = families;
      }
    );
    console.log('this.families:', this.families);
  }

}
