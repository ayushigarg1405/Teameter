import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry } from 'rxjs/operators';

import { Ticket } from './model/Ticket';
import { Log } from './model/Log';

@Injectable({
  providedIn: 'root',
})
export class ProjectTrackingService {
  id: number;

  constructor(private http: HttpClient) {}

  login = (username: string, password: string): Observable<any> => {
    let params = {
      username: username,
      password: password,
    };
    return this.http.post('http://localhost:8080/login', params);
  };

  saveticketid(id: number) {
    this.id = id;
  }

  goBack = (loginId: number): Observable<any> => {
    return this.http.get(`http://localhost:8080/go-back/${loginId}`);
  };
  returnticketid() {
    return this.id;
  }

  retrieveTicketsForUser(id: number): Observable<any> {
    return this.http
      .get<Ticket[]>('http://localhost:8080/ticketByUserId/' + id)
      .pipe(retry(3));
  }

  retrieveHistoryForUser(id: number): Observable<any> {
    return this.http
      .get<Ticket[]>('http://localhost:8080/getCompletedTicket/' + id)
      .pipe(retry(3));
  }

  updateStatus(ticket: Ticket): Observable<any> {
    return this.http
      .post<Ticket>('http://localhost:8080/updateTicket', ticket)
      .pipe(retry(3));
  }

  updateLogWork(log: Log): Observable<any> {
    return this.http
      .post<Log>('http://localhost:8080/saveLog', log)
      .pipe(retry(3));
  }

  retrieveTicketsForAdmin(): Observable<Ticket[]> {
    return this.http
      .get<Ticket[]>('http://localhost:8080/allTickets')
      .pipe(retry(3));
  }

  createTicket(newTicket: Ticket): Observable<any> {
    return this.http
      .post<Ticket>('http://localhost:8080/addticket', newTicket)
      .pipe(retry(3));
  }

  retrieveLastRemainingTime(id: number): Observable<any> {
    return this.http
      .get<number>('http://localhost:8080/getLatestLog/' + id)
      .pipe(retry(3));
  }

  retrieveLogsforTicket(ticketId: number): Observable<Log[]> {
    return this.http
      .get<Log[]>('http://localhost:8080/getLogByTicketId/' + ticketId)
      .pipe(retry(3));
  }

  deleteLog(logid: number): Observable<any> {
    return this.http
      .post<number>('http://localhost:8080/deleteLog/' + logid, {})
      .pipe(retry(3));
  }
}
