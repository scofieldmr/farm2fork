import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from './admin.model';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  private baseUrl = "http://localhost:8070/admin";

  constructor(private http: HttpClient) { 
  }


  fetchCustomerDetailsById(adminId : number | null): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/fetchAdminDetailsById?adminId=${adminId}`);
  }

  updateCustomerDetails(adminId : number | null, customer : Admin): Observable<any>{
    return this.http.put<any>(`${this.baseUrl}/updateAdmin?adminId=${adminId}`, customer)
  }

  deactivateAccount(adminId: number | null): Observable<any>{
    return this.http.put<any>(`${this.baseUrl}/deactivateUser?adminId=${adminId}`, adminId)
  }

  fetchWishlistProducts(){
    return this.http.get<any[]>(`${this.baseUrl}/products/product-demand`);
  }
}
