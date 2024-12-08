import { Component } from '@angular/core';
import { ManageProductService } from '../../../manage-product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})



export class AddProductComponent {

  message = "";
  isSuccess = false;

  constructor(private manageProductService: ManageProductService){}
  addProduct(data: any): void {
    if (data.prodName && data.prodCategory && data.prodQuantity && data.prodPrice) {
      const newProduct: Product = {
        productName: data.prodName,
        productCategory: data.prodCategory,
        quantity: data.prodQuantity,
        price: data.prodPrice
      };
      console.log(newProduct)
      this.manageProductService.addProduct(newProduct).subscribe(
        (response) => {
          this.message = "Product added successfully!";
          this.isSuccess = true;
        },
        (error) => {
          this.message = "Failed to add product. Please try again.";
          this.isSuccess = false;
        }
      );
    }
  }
}

export class Product {
  productName: string = "";
  productCategory: string = "";
  quantity: number = 0;
  price: number = 0;
}
