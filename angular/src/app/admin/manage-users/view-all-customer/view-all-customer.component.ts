import { Component } from '@angular/core';
import { ManageuserService } from '../../../manage-user.service';
import { Customer } from '../../../customer.model';

@Component({
  selector: 'app-view-all-customer',
  templateUrl: './view-all-customer.component.html',
  styleUrl: './view-all-customer.component.css'
})
export class ViewAllCustomerComponent {

  customers : Customer[] = [];
  isFetched: boolean = false; 

  constructor(private manageUserService: ManageuserService) {}

  loadCustomerDetails() {
    this.manageUserService.fetchCustomers().subscribe({
      next: (data) => {
        this.customers = data;
        this.isFetched = true; 
      },
      error: (err) => {
        console.error('Error fetching customers:', err);
        this.isFetched = true; 
      },
    });
  }

}
