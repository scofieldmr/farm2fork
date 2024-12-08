import { Component } from '@angular/core';
import { Customer} from '../../customer.model';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';
import { AdminServiceService } from '../../admin-service.service';
import { Admin } from '../../admin.model';

@Component({
  selector: 'app-update-admin-profile',
  templateUrl: './update-admin-profile.component.html',
  styleUrl: './update-admin-profile.component.css'
})
export class UpdateAdminProfileComponent {

  adminId !: number | null;

  admin !: Admin;

  editMode = false;

  constructor(private adminService : AdminServiceService, private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.adminId = this.authService.getUserId();
    this.fetchCustomerDetails(this.adminId);
  }

  fetchCustomerDetails(customerId : number | null){
    this.adminService.fetchCustomerDetailsById(customerId).subscribe(
      (data) => {this.admin = data},
      (error) => console.log(error)
    )
  }

  updateDetails(customer : Admin)
  {
    this.adminService.updateCustomerDetails(this.adminId, this.admin).subscribe(
      (data) => console.log(data),
      (error) => console.log(error)
    )
  }


  toggleEdit(): void {
    this.editMode = !this.editMode;
  }

  saveChanges(): void {
    this.editMode = false;
  }

}
