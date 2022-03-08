import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from 'src/app/model/Login';
import { ProjectTrackingService } from '../../project-tracking.service';
import { Ticket } from '../../model/Ticket';
import { CreateTicketComponent } from '../create-ticket/create-ticket.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  value: any;
  tickets: Array<Ticket> = [];
  receivedtickets: Array<Ticket> = [];
  texist: boolean;
  iexist: boolean;
  dexist: boolean;
  panelOpenState = false;
  ticketsexist: boolean;
  users: any;
  ticketsnotdone: Array<Ticket> = [];

  uId = [101, 102];
  tuid101: Array<Ticket> = [];
  tuid102: Array<Ticket> = [];
  tuid103: Array<Ticket> = [];
  tuid104: Array<Ticket> = [];
  tuid105: Array<Ticket> = [];

  filtertickets: any;
  login: Login;

  constructor(
    private ticketService: ProjectTrackingService,
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog
  ) {
    this.value = '';
    this.tickets = [];
    this.receivedtickets = [];
    this.tuid101 = [];
    this.tuid102 = [];
    this.tuid103 = [];
    this.tuid104 = [];
    this.tuid105 = [];
    this.filtertickets = [];
    this.texist = false;
    this.iexist = false;
    this.dexist = false;
    this.ticketsexist = true;
    this.users = [
      {
        101: 'Ayushi',
        102: 'Niyati',
        103: 'Snigdha',
        104: 'Bharath',
        105: 'Manihrudhay',
      },
    ];
    this.ticketsnotdone = [];
  }

  ngOnInit(): void {
    this.retrieveTicketsForAdmin();
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
    //console.log("sent data :",data);
    this.router.navigate(['admin-edit-ticket'], {
      queryParams: { data: JSON.stringify(data) },
    });
  }

  openCreateTicket() {
    this.dialog.open(CreateTicketComponent);
  }

  retrieveTicketsForAdmin() {
    this.ticketService
      .retrieveTicketsForAdmin()
      .subscribe((tickets: Ticket[]) => {
        this.tickets = tickets;

        this.filterByUserId();
      });
  }

  filterByUserId() {
    this.tickets.forEach((c) => {
      if (c.status !== 'Done') {
        this.ticketsnotdone = this.ticketsnotdone.concat(c);
      }
    });

    this.ticketsnotdone.forEach((x) => {
      if (x.userId == 101) {
        this.tuid101 = this.tuid101.concat(x);
      } else if (x.userId == 102) {
        this.tuid102 = this.tuid102.concat(x);
      } else if (x.userId == 103) {
        this.tuid103 = this.tuid103.concat(x);
      } else if (x.userId == 104) {
        this.tuid104 = this.tuid104.concat(x);
      } else if (x.userId == 105) {
        this.tuid105 = this.tuid105.concat(x);
      }
    });

    if (this.tuid101.length >= 1) {
      this.filtertickets.push(this.tuid101);
    } else {
      this.filtertickets.push([{ userId: 101 }]);
    }

    if (this.tuid102.length >= 1) {
      this.filtertickets.push(this.tuid102);
    } else {
      this.filtertickets.push([{ userId: 102 }]);
    }

    if (this.tuid103.length >= 1) {
      this.filtertickets.push(this.tuid103);
    } else {
      this.filtertickets.push([{ userId: 103 }]);
    }

    if (this.tuid104.length >= 1) {
      this.filtertickets.push(this.tuid104);
    } else {
      this.filtertickets.push([{ userId: 104 }]);
    }

    if (this.tuid105.length >= 1) {
      this.filtertickets.push(this.tuid105);
    } else {
      this.filtertickets.push([{ userId: 105 }]);
    }
  }

  getfilter(event) {
    var target = event.target || event.srcElement || event.currentTarget;
    var idAttr = target.attributes.id;
    this.value = idAttr.nodeValue;

    this.receivedtickets = [];

    if (this.value === 'todo' && this.texist == false) {
      this.texist = true;
      this.dexist = false;
      this.iexist = false;

      this.tickets.forEach((x) => {
        if (x.status === 'To-Do') {
          this.receivedtickets = this.receivedtickets.concat(x);
        }
      });
      if (this.receivedtickets.length >= 1) {
        this.ticketsexist = true;
      } else {
        this.ticketsexist = false;
      }
    } else {
      this.texist = false;
    }

    if (this.value === 'inprogress' && this.iexist == false) {
      this.texist = false;
      this.dexist = false;
      this.iexist = true;

      this.tickets.forEach((x) => {
        if (x.status === 'In-Progress') {
          this.receivedtickets = this.receivedtickets.concat(x);
        }
      });
      if (this.receivedtickets.length >= 1) {
        this.ticketsexist = true;
      } else {
        this.ticketsexist = false;
      }
    } else {
      this.iexist = false;
    }

    if (this.value === 'done' && this.dexist == false) {
      this.texist = false;
      this.dexist = true;
      this.iexist = false;

      this.tickets.forEach((x) => {
        if (x.status === 'Done') {
          this.receivedtickets = this.receivedtickets.concat(x);
        }
      });
      if (this.receivedtickets.length >= 1) {
        this.ticketsexist = true;
      } else {
        this.ticketsexist = false;
      }
    } else {
      this.dexist = false;
    }
  }
}
