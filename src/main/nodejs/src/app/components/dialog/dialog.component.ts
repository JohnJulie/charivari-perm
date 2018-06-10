import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { PermanenceModel } from '../../shared/models/permanence.model';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit {

  public permanencesToReplace: Array<any>;
  constructor(
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
    this.permanencesToReplace = this.data.permanences;
    console.log('dialog:', this.permanencesToReplace);
  }

  closeDialog(toReplace) {
    this.dialogRef.close(toReplace);
  }

}
