import { Component, OnInit } from '@angular/core';
import { Cart } from '../../cart.model';
import { CustomerService } from '../../customer-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit{

  customerId !: number | null;

  showGeneratePopup : boolean = false;

  constructor(private customerService: CustomerService, private router : Router, private authService: AuthService){}


  showBillPopup = false;

  customerCart : Cart[] = [];

  ngOnInit(){
    this.customerId = this.authService.getUserId();
    this.fetchCart(this.customerId)
  }

  fetchCart(customerId: number | null) : void{
    this.customerService.fetchCustomerCart(customerId).subscribe(
      (data) => this.customerCart = data,
      (error) => console.log(error)
    );
  }

  updateTotalPrice(index: number): void {
    const product = this.customerCart[index];
    product.totalPrice = product.quantity * product.price;
  }

  incrementQuantity(index: number): void {
    const product = this.customerCart[index];
    product.quantity++;
    this.updateTotalPrice(index);
  }

  decrementQuantity(index: number): void {
    const product = this.customerCart[index];
    if (product.quantity > 1) {
      product.quantity--;
      this.updateTotalPrice(index);
    }
  }

  pay(){

    this.showBillPopup = true;
    this.customerService.createOrder(this.customerCart, this.customerId).subscribe(
      (data) => {},
      (error) => {console.log(error)}
    );

    this.customerService.clearCart(this.customerId).subscribe(
      (data) => {},
      (error) => {console.log(error)}
    );
  }

  removeFromCart(index: number): void {
    this.customerService.removeProductFromCart(this.customerCart[index].customerId, this.customerCart[index].productId).subscribe(
      (data) =>{console.log(data)},
      (error) => {console.log(error)}
    )
    this.customerCart.splice(index, 1);
  }

  hasProductsInCart(): boolean{
    return this.customerCart.length > 0;
  }


  getTotalAmount(): number {
    return this.customerCart.reduce((sum, product) => sum + product.totalPrice, 0);
  }

  confirmPurchase(): void {
    this.showBillPopup = false;
    this.router.navigate(["/customer/products"])
    console.log('Purchase confirmed');
  }

}
