import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterByDomainComponent } from './filter-by-domain.component';

describe('FilterByDomainComponent', () => {
  let component: FilterByDomainComponent;
  let fixture: ComponentFixture<FilterByDomainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FilterByDomainComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilterByDomainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
