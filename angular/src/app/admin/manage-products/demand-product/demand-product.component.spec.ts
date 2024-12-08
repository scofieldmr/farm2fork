import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandProductComponent } from './demand-product.component';

describe('DemandProductComponent', () => {
  let component: DemandProductComponent;
  let fixture: ComponentFixture<DemandProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DemandProductComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DemandProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
