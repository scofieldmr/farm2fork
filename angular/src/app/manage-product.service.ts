import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Product } from './product.model';

@Injectable({
  providedIn: 'root'
})
export class ManageProductService {

  prodList1 : Product[] = [];
  message = "";

  private baseUrl = "http://localhost:8070/admin/products"

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/fetchAllProducts`)
  }
  

  addProduct(prod: any): Observable<any> {
    console.log(prod)
    return this.http.post<any>(`${this.baseUrl}/addProduct`, prod).pipe(
      tap((data) => {

      }),
      catchError((error) => {
        this.message = error.message;
        throw error;
      })
    );
  }
  

  deleteProduct(prodId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/deleteProductById?id=${prodId}`).pipe(
      tap(),
    );
  }
  
  updateProductById(id: number, prod: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/updateProductById?id=${id}`, prod);
  }


  addAllProducts(products: any[]): Observable<string> {
    return this.http.post(`${this.baseUrl}/addAll`, products, {
      headers: { 'Content-Type': 'application/json'}, responseType: 'text'}
    );
  }

}
