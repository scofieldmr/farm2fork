import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../customer-service.service';
import { Wishlist } from '../../wishlist.model';
import { Cart } from '../../cart.model';
import { AuthService } from '../../auth.service';


@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrl: './wishlist.component.css'
})
export class WishlistComponent implements OnInit{
  
  wishlist : Wishlist [] = [];

  customerId !: number | null;

  product !: Cart;

  isModalVisible: boolean = false;

  message: string = ""

  constructor(private customerService: CustomerService, private authService : AuthService){}

  ngOnInit(): void {
    this.customerId = this.authService.getUserId()
    this.fetchWishlist(this.customerId);
  }

  fetchWishlist(customerId : number | null):void{
       this.customerService.fetchWishlist(customerId).subscribe(
           (data) => {this.wishlist = data},
           (error) => console.log(error)
       );
  }

  hasItemsInWishlist(): boolean{
    return this.wishlist.length > 0;
  }

  removeFromWishList(index : number)  : void{
    let customerId = this.wishlist[index].customerId;
    let productId = this.wishlist[index].productId;
    this.customerService.removeAnItemFromWishList(customerId, productId).subscribe(
      (data) => console.log(data),
      (error) => console.log(error)
    )
    this.wishlist.splice(index, 1);
  }

  addToCartFromWishList(item : Wishlist) : void{
    this.product = {
      customerId: item.customerId,
      productId: item.productId,
      productName: item.productName,
      quantity: 0,
      price: item.price,
      totalPrice: 0
    };


    this.customerService.addToCartFromWishList(this.product).subscribe(
      (responseMessage) => {
        this.message = responseMessage; 
        this.isModalVisible = true;     
      },
      (error) => console.error(error)
    );
  }

  closeModal(){
    this.isModalVisible = false
  }
}
