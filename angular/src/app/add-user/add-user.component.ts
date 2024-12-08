import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerService } from '../customer-service.service';
import { Router } from '@angular/router';
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {
  customer = {
    customerName: '',
    email: '',
    password: '',
    address: '',
    contactNumber: '',
    emailDomain: '',
    activeUser: ''
  };
  confirmPassword = '';
  passwordMismatch = false;

  emailExisting: boolean = false;


  constructor(private customerService: CustomerService, private router: Router) {}

  addCustomer() {
    if (this.isFormInvalid()) {
      alert('Please fill in all required details.');
      return;
    }

    this.customer.password = CryptoJS.SHA256(this.customer.password).toString();

    this.customer.emailDomain = this.customer.email.split("@")[1]

    this.customer.activeUser = "yes"

    console.log(this.customer)

    this.customerService.addCustomer(this.customer).subscribe({
      next: (response) => {
        console.log(this.customer);
      },
      error: (error) => {
        console.error('There was an error adding the customer!', error);
      }
    });
    
    
    this.router.navigate(["/login"])
  }

  checkPasswords() {
    this.passwordMismatch = this.customer.password !== this.confirmPassword;
  }

  private isFormInvalid(): boolean {
    return (
      !this.customer.customerName ||
      !this.customer.email ||
      !this.customer.password ||
      !this.customer.contactNumber ||
      this.passwordMismatch
    );
  }

  emailExists(email: string){
    console.log(email)
    this.customerService.emailExistingInGrocery(email).subscribe(
      (response) =>{ 
        this.emailExisting = response.exists;
      },
      (error) => console.log(error)
    )
  }

}
