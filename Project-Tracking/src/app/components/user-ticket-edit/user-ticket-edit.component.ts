import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { MatDialog } from '@angular/material/dialog';
import { LogWorkComponent } from '../log-work/log-work.component';
import { Ticket } from '../../model/Ticket';
import { ProjectTrackingService } from '../../project-tracking.service';
import { Login } from 'src/app/model/Login';
import { Log } from 'src/app/model/Log';

@Component({
  selector: 'app-user-ticket-edit',
  templateUrl: './user-ticket-edit.component.html',
  styleUrls: ['./user-ticket-edit.component.css'],
})
export class UserTicketEditComponent implements OnInit {
  ticketForm!: FormGroup;
  received_data: any;
  login: Login;
  updated_data: Ticket;
  alert: string;
  warning: string;
  remainingTimeOfTicket: number;
  checktime: boolean = false;
  logs: Array<Log> = [];
  logExist: boolean;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog,
    private ticketService: ProjectTrackingService
  ) {
    this.received_data = [];
    this.updated_data = {
      ticketId: 0,
      userId: 0,
      title: '',
      description: '',
      comments: '',
      status: '',
      estimatedTime: 2,
    };
  }

  ngOnInit(): void {
    this.ticketForm = new FormGroup({
      com: new FormControl(''),
      status: new FormControl(''),
    });

    this.route.queryParams.subscribe((params) => {
      this.received_data = JSON.parse(params.data);

      this.ticketService.saveticketid(this.received_data[0]['ticketId']);
      this.retrieveLogsforTicket(this.received_data[0]['ticketId']);
    });

    this.enableLog();
  }

  goback() {
    this.ticketService
      .goBack(this.received_data[0]['userId'])
      .subscribe((response) => {
        this.login = response;
        this.router.navigate(['user'], {
          queryParams: { login: JSON.stringify(this.login) },
          skipLocationChange: true,
        });
      });
  }

  get com() {
    return this.ticketForm.get('com') as FormControl;
  }

  get status() {
    return this.ticketForm.get('status') as FormControl;
  }

  openDialog() {
    this.dialog.open(LogWorkComponent);
  }
  updateStatus() {
    this.received_data.forEach((x) => {
      this.updated_data = x;
    });

    if (this.com.value == '' && this.status.value == '') {
      this.warning = 'Please add comments or update status!';
    } else {
      this.warning = '';
      if (this.status.value === '') {
        console.log('values empty');

        this.updated_data.status = this.received_data[0]['status'];
        console.log(this.updated_data.status);
        console.log(this.received_data[0]['status']);
      } else {
        this.updated_data.status = this.status.value;
      }

      this.updated_data.comments = this.com.value;
      this.ticketService.updateStatus(this.updated_data).subscribe(
        (ticket: Ticket) => {
          this.alert = 'Your details has been updated!';
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

  enableLog() {
    this.ticketService
      .retrieveLastRemainingTime(this.received_data[0]['ticketId'])
      .subscribe((time: number) => {
        if (time <= 0) this.checktime = true;
      });
  }

  retrieveLogsforTicket(ticketId: number) {
    this.ticketService
      .retrieveLogsforTicket(ticketId)
      .subscribe((logs: Log[]) => {
        this.logs = logs;
        if (this.logs[0].timeSpent !== 0) {
          this.logExist = true;
        }
      });
  }

  deleteLog(logid: number) {
    console.log(logid);
    this.ticketService.deleteLog(logid).subscribe((response) => {
      window.location.reload();
    });
  }
}
