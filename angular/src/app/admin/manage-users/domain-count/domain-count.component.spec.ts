import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DomainCountComponent } from './domain-count.component';

describe('DomainCountComponent', () => {
  let component: DomainCountComponent;
  let fixture: ComponentFixture<DomainCountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DomainCountComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DomainCountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
