import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import * as CryptoJS from 'crypto-js';
import { CustomerService } from '../customer-service.service';
import { ViewChild, ElementRef } from '@angular/core';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string | null = null;
  deactivated: boolean = false; 
  isLoading: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private customerService: CustomerService
  ) {}

  onSubmit(event: Event) {
    event.preventDefault();

    if (this.deactivated) {
      return;
    }

    const hashedPassword = CryptoJS.SHA256(this.password).toString();

    this.authService.login({ email: this.email, password: hashedPassword }).subscribe(
      (response) => {
        if (response.status === 'success') {
          if (response.message === 'admin') {
            this.router.navigate(['admin']);
          } else if (response.message === 'customer') {
            localStorage.setItem('email', this.email);
            this.router.navigate(['customer']);
          }
        } 
        else if (response.status === 'deactivated') {
          this.deactivated = true;
          this.errorMessage = 'Your account is deactivated. Please reactivate it to log in.';
        } 
        else {
          this.errorMessage = response.message;
        }
      },
      (error) => {
        this.errorMessage = 'An error occurred during login.';
        console.error(error);
      }
    );
  }

  reactivate() {
    this.isLoading = true;
  
    this.customerService.activateAccount(this.email).subscribe(
      (response) => {
        this.isLoading = false;
        this.deactivated = false; 
        this.errorMessage = 'Your account has been successfully reactivated. You can now log in.';
        console.log(response);
      },
      (error) => {
        this.isLoading = false;
        this.errorMessage = 'Error reactivating account. Please try again.';
        console.error(error);
      }
    );
  }
}


