import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminServiceService } from '../../../admin-service.service';

@Component({
  selector: 'app-demand-product',
  templateUrl: './demand-product.component.html',
  styleUrl: './demand-product.component.css'
})
export class DemandProductComponent {

  products: any[] = [];

  constructor(private http: HttpClient, private adminService: AdminServiceService) {}

  ngOnInit(): void {
    this.fetchWishlistProducts();
  }

  fetchWishlistProducts() {
    
    this.adminService.fetchWishlistProducts().subscribe(
      (response) => {
        this.products = response;
      },
      (error) => {
        console.error('Error fetching wishlist products:', error);
      }
    );
  }

}
