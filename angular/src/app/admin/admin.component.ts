import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {

  constructor(private router: Router) { }

  goToManageProducts() {
    this.router.navigate(['admin/manage-products']);
  }

  goToManageUsers() {
    this.router.navigate(['admin/manage-users']);
  }

  goToUpdateProfile(){
    this.router.navigate(["admin/update-profile"])
  }
goTologout(){
this.router.navigate(["login"])

}

}
