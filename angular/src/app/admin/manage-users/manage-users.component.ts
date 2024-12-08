import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrl: './manage-users.component.css'
})
export class ManageUsersComponent {

  constructor(private router: Router) { }

  filterByDomain() {
    this.router.navigate(['/admin/filter-by-domain']);
  }

  domainCount()
  {
    this.router.navigate(['/admin/domain-count']);
  }

  viewAllCustomer()
  {
    this.router.navigate(["/admin/view-all-customers"])
  }
  
}
