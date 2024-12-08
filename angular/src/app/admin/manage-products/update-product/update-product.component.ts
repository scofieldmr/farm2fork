import { Component } from '@angular/core';
import { ManageProductService } from '../../../manage-product.service';
import { Product } from '../add-product/add-product.component';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrl: './update-product.component.css'
})
export class UpdateProductComponent {

  message = "";
  isSuccess = false;

  constructor(private manageProductService: ManageProductService){}

  updateProduct(data: any): void {
    if (data.prodId && data.prodName && data.prodCategory && data.prodQuantity && data.prodPrice) {
      const newProduct: any = {
        productId: data.prodId,
        productName: data.prodName,
        productCategory: data.prodCategory,
        quantity: data.prodQuantity,
        price: data.prodPrice
      };
      console.log(newProduct);
      this.manageProductService.updateProductById(data.prodId, newProduct).subscribe(
        (response) => {
          this.message = "Product updated successfully!";
          this.isSuccess = true;
        },
        (error) => {
          this.message = "Failed to update product. Please try again.";
          this.isSuccess = false;
        }
      );
    }
  }

}
