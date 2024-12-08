import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './product.model';
import { Cart } from './cart.model';
import { Wishlist } from './wishlist.model';
import { Customer } from './customer.model';
import { Register } from './register.model';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = "http://localhost:8070/customer"

  productQuantity : number = 0;

  constructor(private http: HttpClient) { }

  addCustomer(customer: Register){
    return this.http.post<any>(`${this.baseUrl}/registerCustomer`, customer);
  }

  emailExistingInGrocery(email: string) {
    return this.http.get<{ exists: boolean }>(`${this.baseUrl}/emailExists?email=${email}`);
  }

  fetchCategories(): Observable<String[]>{
    return this.http.get<String[]>(`${this.baseUrl}/availableCategories`)
  }
  
  sortProductByOrder(sort: string): Observable<Product[]>{
    return this.http.get<any>(`${this.baseUrl}/sortProducts?order=${sort}`)
  }

  fetchAllProducts(): Observable<Product[]>{
    return this.http.get<any>(`${this.baseUrl}/fetchAllProducts`);
  }

  filterByCategory(category: string): Observable<Product[]>{
    return this.http.get<any>(`${this.baseUrl}/filterByCategory?category=${category}`)
  }

  filterByCategoryByOrder(category: string, sort: String): Observable<Product[]>{
    console.log(category, sort)
    return this.http.get<any>(`${this.baseUrl}/filterByCategoryByOrder?category=${category}&order=${sort}`)
  }


  addToCart(product: Cart): Observable<string>{
    return this.http.post(`${this.baseUrl}/addToCart`,
       product, {responseType: 'text'})
    };

  fetchCustomerCart(customerId : number | null): Observable<Cart[]>{
    return this.http.get<any>(`${this.baseUrl}/fetchCart?customerId=${customerId}`)
  }

  createOrder(cartItems: Cart[], customerId : number | null): Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/createOrder?customerId=${customerId}`, cartItems);
  }

  fetchOrderHistory(customerId : number | null): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/orderHistory?customerId=${customerId}`);
  }

  clearCart(customerId : number | null): Observable<any>{
     return this.http.delete<any>(`${this.baseUrl}/clearCartAfterPay?customerId=${customerId}`);
  }

  removeProductFromCart(customerId: number | null, productId : number): Observable<any>{
   return this.http.delete<any>(`${this.baseUrl}/removeItemFromCart?customerId=${customerId}&productId=${productId}`);
  }

  addToWishlist(product: Wishlist): Observable<string>{
    return this.http.post(`${this.baseUrl}/addToWishlist`, product, {responseType: 'text'})
  };


  fetchWishlist(customerId:number | null): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/fetchWishlist?customerId=${customerId}`);
  }

  removeAnItemFromWishList(customerId : number | null, productId : number): Observable<any>{
    return this.http.delete<any>(`${this.baseUrl}/removeFromWishlist?customerId=${customerId}&productId=${productId}`);
  }

  addToCartFromWishList(product: Cart): Observable<string>{
    return this.http.post(`${this.baseUrl}/addToCart`,
       product, {responseType: 'text'})
    };

  fetchCustomerDetailsById(customerId : number | null): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/fetchCustomerDetailsById?customerId=${customerId}`);
  }

  updateCustomerDetails(customerId : number | null, customer : Customer): Observable<any>{
    return this.http.put<any>(`${this.baseUrl}/updateCustomer?customerId=${customerId}`, customer)
  }

  deactivateAccount(customerId: number | null): Observable<any>{
    return this.http.put<any>(`${this.baseUrl}/deactivateUser?customerId=${customerId}`, customerId)
  }

  checkIfProductInCart(customerId: number, productId: number): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/checkIfProductInCart?customerId=${customerId}&productId=${productId}`);
  }

  activateAccount(email: string): Observable<any>{
    return this.http.put(`${this.baseUrl}/activateUser?email=${email}`, null, {
      responseType: 'text'
    });
  }

  searchProductByName(name: string): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/searchProductByName?productName=${name}`)
  }

}
