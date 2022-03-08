import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './components/admin/admin.component';
import { LoginComponent } from './components/login/login.component';
import { UserTicketComponent } from './components/user-ticket/user-ticket.component';
import { UserTicketEditComponent } from './components/user-ticket-edit/user-ticket-edit.component';
import { UserHistoryComponent } from './components/user-history/user-history.component';
import { AdminTicketEditComponent } from './components/admin-ticket-edit/admin-ticket-edit.component';

const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
  },
  {
    path: 'user',
    component: UserTicketComponent,
  },
  {
    path: '',
    component: LoginComponent,
  },
  {
    path: 'edit-ticket',
    component: UserTicketEditComponent,
  },
  {
    path: 'user-history',
    component: UserHistoryComponent,
  },
  {
    path: 'admin-edit-ticket',
    component: AdminTicketEditComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
