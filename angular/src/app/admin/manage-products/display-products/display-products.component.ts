import { Component } from '@angular/core';
import { ManageProductService } from '../../../manage-product.service';
import { Product } from '../../../product.model';

@Component({
  selector: 'app-display-products',
  templateUrl: './display-products.component.html',
  styleUrl: './display-products.component.css'
})
export class DisplayProductsComponent {

  constructor(private manageProductService: ManageProductService){}

  message = "";
  prodList1: Product[] = [];
  product = "";

  ngOnInit() {
    this.loadProducts();
  }
  loadProducts(): any {
    this.manageProductService.getProducts().subscribe(
      (data) => {
        this.prodList1 = data;
      },
      (error) => {
        console.error(
          'Error Fetching products:',
          (this.message = 'There is error'),
          error
        );
      }
    );
    console.log(this.prodList1)
  }

}
