import { Component, OnInit } from '@angular/core';
import { Ticket } from '../../model/Ticket';
import { ProjectTrackingService } from '../../project-tracking.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Login } from 'src/app/model/Login';

@Component({
  selector: 'app-user-ticket',
  templateUrl: './user-ticket.component.html',
  styleUrls: ['./user-ticket.component.css'],
})
export class UserTicketComponent implements OnInit {
  login: Login;

  description: string;
  userId: number;
  ticketsexist = false;

  tickets: Array<Ticket> = [];
  todotickets: Array<Ticket> = [];

  constructor(
    private ticketService: ProjectTrackingService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.tickets = [];
    this.todotickets = [];
    this.userId = 0;
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.login = JSON.parse(params['login']);
    });

    this.userId = this.login.loginId;

    this.retrieveTicketsForUser();
  }

  retrieveTicketsForUser() {
    this.ticketService.retrieveTicketsForUser(this.userId).subscribe(
      (tickets: Ticket[]) => {
        this.tickets = tickets;

        this.tickets.forEach((x) => {
          if (x.status === 'To-Do' || x.status === 'In-Progress') {
            this.todotickets = this.todotickets.concat(x);
          }
        });

        if (this.todotickets.length >= 1) {
          this.ticketsexist = true;
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  navigate(
    ticketId: any,
    title: any,
    description: any,
    comments: any,
    status: any,
    estimatedTime: any,
    userId: any
  ) {
    let data = [
      {
        ticketId: ticketId,
        title: title,
        description: description,
        comments: comments,
        status: status,
        estimatedTime: estimatedTime,
        userId: userId,
      },
    ];

    this.router.navigate(['edit-ticket'], {
      queryParams: { data: JSON.stringify(data) },
    });
  }

  goToUserHistory() {
    let data = [{ userId: this.userId }];
    this.router.navigate(['user-history'], {
      queryParams: { data: JSON.stringify(data) },
      skipLocationChange: true,
    });
  }
}
