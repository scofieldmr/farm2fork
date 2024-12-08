import { Component } from '@angular/core';
import { Customer } from '../../../customer.model';
import { ManageuserService } from '../../../manage-user.service';

@Component({
  selector: 'app-filter-by-domain',
  templateUrl: './filter-by-domain.component.html',
  styleUrl: './filter-by-domain.component.css'
})
export class FilterByDomainComponent {
  domains: string[] = [];
  customers: Customer[] = [];
  selectedDomain: string = '';

  constructor(private managerUserService: ManageuserService) {}

  ngOnInit(): void {
    this.loadDomains();
  }

  loadDomains(): void {
    this.managerUserService.getEmailDomains().subscribe(
      (data) => (this.domains = data),
      (error) => console.error('Error loading domains', error)
    );
  }

  onDomainSelect(): void {
    if (this.selectedDomain) {
      this.managerUserService.getCustomerByEmailDomain(this.selectedDomain).subscribe(
        (data) => (this.customers = data),
        (error) => console.error('Error loading customers', error)
      );
    }
  }
}
