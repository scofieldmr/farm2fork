import { Component } from '@angular/core';
import { CustomerService } from '../../customer-service.service';
import { Product } from '../../product.model';

@Component({
  selector: 'app-search-product-by-name',
  templateUrl: './search-product-by-name.component.html',
  styleUrl: './search-product-by-name.component.css'
})
export class SearchProductByNameComponent {
  searchName: string = '';  
  products !: Product | null;    
  message: string = '';  

  constructor(private customerService: CustomerService) {}

  onSearch(): void {
    if (this.searchName.trim() === '') {
      this.message = 'Please enter a product name.';
      this.products = null;
      return;
    }

    console.log(this.searchName)

    this.customerService.searchProductByName(this.searchName).subscribe(
      (data) => {
        if (data === null) {
          this.message = 'No products found.';
        } else {
          this.message = '';
          this.products = data;
          console.log(this.products)
        }
      },
      (error) => {
        this.message = 'Error fetching products.';
        this.products = null;
      }
    );
  }
}
