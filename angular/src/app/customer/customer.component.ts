import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent implements OnInit{
  email !: String | null;
  showLogout: boolean = false;

  constructor(private authService: AuthService, private router: Router){}

  ngOnInit(): void {
      this.email = localStorage.getItem("email")
  }

  toggleLogout(): void {
    this.showLogout = !this.showLogout;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
