import { Component, OnInit } from '@angular/core';
import { Customer } from '../../customer.model';
import { CustomerService } from '../../customer-service.service';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.css'
})
export class MyProfileComponent implements OnInit {

  customerId !: number | null;

  customer !: Customer;

  editMode = false;

  constructor(private customerService : CustomerService, private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.customerId = this.authService.getUserId();
    this.fetchCustomerDetails(this.customerId);
  }

  fetchCustomerDetails(customerId : number | null){
    this.customerService.fetchCustomerDetailsById(customerId).subscribe(
      (data) => {this.customer = data},
      (error) => console.log(error)
    )
  }

  updateDetails(customer : Customer)
  {
    this.customerService.updateCustomerDetails(this.customerId, customer).subscribe(
      (data) => console.log(data),
      (error) => console.log(error)
    )
  }

  deactivateAccount(customerId : number | null){
    this.customerService.deactivateAccount(customerId).subscribe(
      (data) => console.log(data),
      (error) => console.log(error)
    )

    this.router.navigate([""])
  }

  toggleEdit(): void {
    this.editMode = !this.editMode;
  }

  saveChanges(): void {
    this.editMode = false;
  }
}

