import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UserTicketEditComponent } from '../user-ticket-edit/user-ticket-edit.component';
import { Log } from '../../model/Log';
import { ProjectTrackingService } from '../../project-tracking.service';
import { Ticket } from 'src/app/model/Ticket';

@Component({
  selector: 'app-log-work',
  templateUrl: './log-work.component.html',
  styleUrls: ['./log-work.component.css'],
})
export class LogWorkComponent implements OnInit {
  ticketForm!: FormGroup;

  id: number;

  lastRemainingTime: number;

  logwork: Log;
  warning: string;

  constructor(
    public dialog: MatDialogRef<LogWorkComponent>,
    private ticketService: ProjectTrackingService
  ) {
    this.logwork = {
      logId: 0,
      ticketId: 0,
      timeSpent: 0,
      timeRemaining: 0,
      date: '',
      description: '',
    };
  }

  ngOnInit(): void {
    this.id = this.ticketService.returnticketid();

    console.log('getting id :', this.id);

    this.ticketService.retrieveLastRemainingTime(this.id).subscribe(
      (time: number) => {
        this.lastRemainingTime = time;
        console.log(this.lastRemainingTime);
      },
      (error) => {
        console.log(error);
      }
    );

    this.ticketForm = new FormGroup({
      timeSpent: new FormControl(''),
      timeRemaining: new FormControl(''),
      date: new FormControl(''),
      description: new FormControl(''),
    });
  }
  get timeSpent() {
    return this.ticketForm.get('timeSpent') as FormControl;
  }
  get timeRemaining() {
    return this.ticketForm.get('timeRemaining') as FormControl;
  }
  get date() {
    return this.ticketForm.get('date') as FormControl;
  }

  get description() {
    return this.ticketForm.get('description') as FormControl;
  }

  addToLog() {
    if (
      this.timeSpent.value == '' ||
      this.date.value == '' ||
      this.description.value === ''
    ) {
      this.warning = 'Please enter the required fields!';
    } else {
      this.warning = '';
      console.log(this.date.value);

      this.logwork = {
        logId: 0,
        ticketId: this.id,
        timeSpent: this.timeSpent.value,
        timeRemaining: this.lastRemainingTime - this.timeSpent.value,
        date: this.date.value,
        description: this.description.value,
      };
      console.log(this.logwork);
      console.log(this.lastRemainingTime - this.timeSpent.value);
      this.ticketService.updateLogWork(this.logwork).subscribe(
        (log: Log) => {
          console.log(log);
          window.location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
      this.dialog.close();
    }
  }
}
