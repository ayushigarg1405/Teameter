import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/app/model/Login';
import { ProjectTrackingService } from '../../project-tracking.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  warning: string = '';
  login: Login;

  constructor(
    private projectTrackingservice: ProjectTrackingService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  Login() {
    if (this.username == undefined || this.password == undefined)
      this.warning = 'Please provide username and password';
    else {
      this.projectTrackingservice
        .login(this.username, this.password)
        .subscribe((response) => {
          this.login = response;
          if (this.login.designation === 'admin')
            this.router.navigate(['admin']);
          else if (this.login.loginId == 0)
            this.warning = 'The username entered does not exist';
          else
            this.router.navigate(['user'], {
              queryParams: { login: JSON.stringify(this.login) },
              skipLocationChange: true,
            });
        });
    }
  }
}
