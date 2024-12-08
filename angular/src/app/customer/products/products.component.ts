import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../customer-service.service';
import { Product } from '../../product.model';
import { Cart } from '../../cart.model';
import { Wishlist } from '../../wishlist.model';
import { AuthService } from '../../auth.service';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {

  customerId !: number | null;

  products: Product[] = [];

  categories: String[] = [];

  addToCartProduct!: Cart;

  selectedCategory : string = "";

  wishlistProduct !: Wishlist;

  message: string = ''; 

  isModalVisible: boolean = false;
  
  sort: string | null = null;

  product !: Product ;


  constructor(private customerService: CustomerService, private authService: AuthService){}

  ngOnInit(): void {
    this.customerId = this.authService.getUserId();
    this.fetchAllProducts()
    this.loadCategories()
     }

  loadCategories(): void{
    this.customerService.fetchCategories().subscribe(
      (data) => this.categories = data,
      (error) => console.log(error)
    )
  }

  fetchAllProducts(): void{
    this.customerService.fetchAllProducts().subscribe(
      (data) => (this.products = data),
      (error) => console.error(error)
    );
  }

   addToCart(product: Product): void{
     this.addToCartProduct = {
       customerId : this.customerId,
       productId : product.productId,
       productName : product.productName,
       quantity: product.selectedQuantity,
       price: product.price,
       totalPrice : product.selectedQuantity * product.price
     };

     this.customerService.addToCart(this.addToCartProduct).subscribe(
      (responseMessage) => {
        this.message = responseMessage; 
        this.isModalVisible = true;     
      },
      (error) => console.error(error)
    );

   }

   closeModal() {
    this.isModalVisible = false; 
  }

   addToWishlist(product : Product): void{
        this.wishlistProduct= {
          customerId : this.customerId,
          productId : product.productId,
          productName : product.productName,
          quantity: product.quantity,
          price: product.price
        };

      this.customerService.addToWishlist(this.wishlistProduct).subscribe(
        (responseMessage) => {
          this.message = responseMessage; 
          this.isModalVisible = true;     
        },
        (error) => console.error(error)
      );
   }

   sortProducts(order: string){
    this.sort = order;
    if(!this.selectedCategory){
      this.customerService.sortProductByOrder(this.sort).subscribe(
        (data) => this.products = data,
        (error) => console.log(error)
      )
    }else{
      this.customerService.filterByCategoryByOrder(this.selectedCategory, this.sort).subscribe(
        (data) => this.products = data,
        (error) => console.log(error)
      )
    }
   }

   filterByCategory(){
    console.log(this.selectedCategory)
    if(this.selectedCategory === "" && this.sort){
      this.sortProducts(this.sort)
    }
    else if(this.sort === null){
      this.customerService.filterByCategory(this.selectedCategory).subscribe(
        (data) => {this.products = data
          console.log(this.products)
        },
        (error) => console.log(error)
      )
    }else{
      this.customerService.filterByCategoryByOrder(this.selectedCategory, this.sort).subscribe(
        (data) => this.products = data,
        (error) => console.log(error)
      )}
   }

   resetFilter(){
    this.sort = null;
    if(this.selectedCategory === ""){
      this.fetchAllProducts()
    }else{
      this.filterByCategory()
    }
   }

  searchName: string = ''; 
  onSearch(): void {
    if (this.searchName.trim() === '') {
      this.message = 'Please enter a product name.';
      return;
    }

    
    this.customerService.searchProductByName(this.searchName).subscribe(
      (data) => {
        if (data.length === 0) {
          this.message = 'No products found.';
        } else {
          this.message = '';
          this.product = data;
          console.log(this.products)
        }
      },
      (error) => {
        this.message = 'Error fetching products.';
      }
    );
  }
}
