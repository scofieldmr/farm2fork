import { Component } from '@angular/core';
import { ManageuserService } from '../../../manage-user.service';

@Component({
  selector: 'app-domain-count',
  templateUrl: './domain-count.component.html',
  styleUrl: './domain-count.component.css'
})
export class DomainCountComponent {

  emailDomainCounts: { domain: string, count: number }[] = [];

  constructor(private domainService: ManageuserService) { }

  ngOnInit(): void {
    this.fetchEmailDomainCounts();
  }

  fetchEmailDomainCounts(): void {
    this.domainService.getCustomerCountByEmailDomain().subscribe(
      (data) => {
        this.emailDomainCounts = Object.entries(data)
          .map(([domain, count]) => ({ domain, count }))
          .sort((a, b) => a.count - b.count);
      },
      (error) => {
        console.error("Error fetching domain counts", error);
      }
    );
  }

}
