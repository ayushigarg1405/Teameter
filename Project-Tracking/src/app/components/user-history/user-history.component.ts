import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Ticket } from '../../model/Ticket';
import { ProjectTrackingService } from '../../project-tracking.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Login } from 'src/app/model/Login';

@Component({
  selector: 'app-user-history',
  templateUrl: './user-history.component.html',
  styleUrls: ['./user-history.component.css'],
})
export class UserHistoryComponent implements OnInit {
  tickets: Array<Ticket> = [];
  donetickets: Array<Ticket> = [];

  userId: number;
  doneticketsexist = false;

  received_data: any;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ticketService: ProjectTrackingService,
    private location: Location
  ) {
    this.donetickets = [];
    this.tickets = [];
    this.userId = 0;
  }

  login: Login;

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.received_data = JSON.parse(params.data);

      this.userId = this.received_data[0]['userId'];
    });

    this.retrieveHistoryForUser();
  }

  goBack() {
    this.ticketService.goBack(this.userId).subscribe((response) => {
      this.login = response;
      this.router.navigate(['user'], {
        queryParams: { login: JSON.stringify(this.login) },
        skipLocationChange: true,
      });
    });
  }

  retrieveHistoryForUser() {
    this.ticketService.retrieveHistoryForUser(this.userId).subscribe(
      (tickets: Ticket[]) => {
        this.donetickets = tickets;

        if (this.donetickets.length >= 1) {
          this.doneticketsexist = true;
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
