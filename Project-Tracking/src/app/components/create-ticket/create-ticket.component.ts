import { Component, OnInit } from '@angular/core';
import { Ticket } from '../../model/Ticket';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { ProjectTrackingService } from '../../project-tracking.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css'],
})
export class CreateTicketComponent implements OnInit {
  createForm!: FormGroup;
  selectedItem!: string;
  ct!: Ticket;
  selectedUser!: number;
  alert: string;
  warning: string;

  statuses = [
    { value: 'To-Do', viewValue: 'To-Do' },
    { value: 'In-Progress', viewValue: 'In-Progress' },
    { value: 'Done', viewValue: 'Done' },
  ];

  users = [
    { value: 101, viewvalue: 'Ayushi' },
    { value: 102, viewvalue: 'Niyati' },
    { value: 103, viewvalue: 'Snigdha' },
    { value: 104, viewvalue: 'Bharat' },
    { value: 105, viewvalue: 'Manihrudhay' },
  ];

  constructor(
    public dialog: MatDialogRef<CreateTicketComponent>,
    private ticketService: ProjectTrackingService
  ) {
    this.ct = {
      ticketId: 0,
      userId: 0,
      title: 'add functions',
      status: 'todo',
      description: 'report asap',
      comments: 'Just added',
      estimatedTime: 10,
    };
  }

  ngOnInit(): void {
    this.createForm = new FormGroup({
      userId: new FormControl(''),
      title: new FormControl(''),
      description: new FormControl(''),
      status: new FormControl(''),
      comments: new FormControl(''),
      estimatedTime: new FormControl(''),
    });
  }
  get userId() {
    return this.createForm.get('userId') as FormControl;
  }
  get title() {
    return this.createForm.get('title') as FormControl;
  }
  get description() {
    return this.createForm.get('description') as FormControl;
  }
  get comments() {
    return this.createForm.get('comments') as FormControl;
  }
  get status() {
    return this.createForm.get('status') as FormControl;
  }
  get estimatedTime() {
    return this.createForm.get('estimatedTime') as FormControl;
  }

  addTicket() {
    if (
      this.selectedUser == null ||
      this.title.value == '' ||
      this.description.value == '' ||
      this.estimatedTime.value == ''
    ) {
      this.warning = 'please enter required fields!';
    } else {
      this.warning = '';
      this.ct = {
        ticketId: 0,
        userId: this.selectedUser,
        title: this.title.value,
        status: 'To-Do',
        description: this.description.value,
        comments: this.comments.value,
        estimatedTime: this.estimatedTime.value,
      };
      this.ticketService.createTicket(this.ct).subscribe(
        (response) => {
          this.alert = 'Ticket is successfully assigned!';
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
