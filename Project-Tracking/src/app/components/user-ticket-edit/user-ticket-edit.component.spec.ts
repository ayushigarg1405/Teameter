import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTicketEditComponent } from './user-ticket-edit.component';

describe('UserTicketEditComponent', () => {
  let component: UserTicketEditComponent;
  let fixture: ComponentFixture<UserTicketEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserTicketEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserTicketEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
