import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Ticket } from '../../model/Ticket';
import { ProjectTrackingService } from '../../project-tracking.service';
import { Login } from 'src/app/model/Login';
import { Log } from 'src/app/model/Log';

@Component({
  selector: 'app-admin-ticket-edit',
  templateUrl: './admin-ticket-edit.component.html',
  styleUrls: ['./admin-ticket-edit.component.css']
})
export class AdminTicketEditComponent implements OnInit {

  ticketForm!: FormGroup;
  received_data: any;
  login:Login;
  updated_data: Ticket;
  selectedItem:string;
  selectedUser:number;
  alert:string;
  logs: Array<Log> = [];
  logExist:boolean;
  

  ticket:any={
    title:"ticket title",
    ticketId:1,
    description:"ticket description",
    comments:"ticket comments",
    status:"To-Do"
  }

  statuses = [
    {value: 'To-Do', viewValue: 'To-Do'},
    {value: 'In-Progress', viewValue: 'In-Progress'},
    {value: 'Done', viewValue: 'Done'}
  ];

  constructor(private router: Router,
    private route: ActivatedRoute,
    private ticketService: ProjectTrackingService) {
      this.received_data = [];
      this.updated_data = { 
        ticketId: 0,
        userId: 0,
        title: '',
        description: '',
        comments: '', 
        status: 'To-Do',
        estimatedTime: 2,
      };
     }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.received_data = JSON.parse(params.data);
      
      this.ticketService.saveticketid(this.received_data[0]['ticketId']);
      this.retrieveLogsforTicket(this.received_data[0]['ticketId']);
    });
    this.ticketForm = new FormGroup({
      com: new FormControl(this.received_data[0]['comments']),
      desc: new FormControl(this.received_data[0]['description']),
      title:new FormControl(this.received_data[0]['title']),
      estimatedTime:new FormControl(this.received_data[0]['estimatedTime'])
    });
  }

  goback() {
    
    this.ticketService
      .goBack(this.received_data[0]['userId'])
      .subscribe((response) => {
        this.login = response;
        this.router.navigate(['admin'], {
          queryParams: { login: JSON.stringify(this.login) },
          skipLocationChange: true,
        });
      });
  }

  get com() { 
    return this.ticketForm.get('com') as FormControl;
  }


  get title() { 
    return this.ticketForm.get('title') as FormControl;
  }

  get desc() { 
    return this.ticketForm.get('desc') as FormControl;
  }

  get estimatedTime() { 
    return this.ticketForm.get('estimatedTime') as FormControl;
  }

  updateAdmin(){
    this.received_data.forEach((x) => {
      this.updated_data = x;
    });
   
    
    this.updated_data.comments = this.com.value;
    this.updated_data.title=this.title.value;
    this.updated_data.description=this.desc.value;
    this.updated_data.estimatedTime=this.estimatedTime.value;
   console.log(this.updated_data);
    this.ticketService.updateStatus(this.updated_data)
    .subscribe((response) => {
      response = null;
      
      this.ngOnInit();
      
    });
    this.alert="Ticket is successfully updated!";
    
  }

  retrieveLogsforTicket(ticketId:number)
  {
    this.ticketService.retrieveLogsforTicket(ticketId).subscribe((logs:Log[])=>{
      this.logs=logs;
      if(this.logs[0].timeSpent!==0){
          this.logExist=true;
      }
    })
    
  }


}
