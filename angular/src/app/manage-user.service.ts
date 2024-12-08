import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './customer.model';

@Injectable({
  providedIn: 'root'
})
export class ManageuserService {
  private baseUrl = "http://localhost:8070/admin/customerManage"

  constructor(private http: HttpClient) {}

  getEmailDomains(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/fetchEmailDomains`);
  }

  getCustomerByEmailDomain(category: string): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.baseUrl}/getCustomerByDomain/?domain=${category}`);
  }

  getCustomerCountByEmailDomain(): Observable<{ [key: string]: number }> {
    return this.http.get<{ [key: string]: number }>(`${this.baseUrl}/emailDomainCount`);
  }

  fetchCustomers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/fetchAllCustomers`);
  }
}
