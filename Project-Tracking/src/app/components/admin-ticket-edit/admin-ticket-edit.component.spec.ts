import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTicketEditComponent } from './admin-ticket-edit.component';

describe('AdminTicketEditComponent', () => {
  let component: AdminTicketEditComponent;
  let fixture: ComponentFixture<AdminTicketEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminTicketEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTicketEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
