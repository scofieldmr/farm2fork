import { Component } from '@angular/core';
import { ManageProductService } from '../../../manage-product.service';

@Component({
  selector: 'app-delete-product',
  templateUrl: './delete-product.component.html',
  styleUrl: './delete-product.component.css'
})
export class DeleteProductComponent {

  constructor(private manageProductService: ManageProductService){}

  successMessage: string = '';
  errorMessage: string = '';

  deleteProduct(prodId: number) {
    this.manageProductService.deleteProduct(prodId).subscribe({
      next: () => {
        this.successMessage = `Product with ID ${prodId} deleted successfully.`;
        this.errorMessage = ''; 
      },
      error: (err) => {
        this.errorMessage = `Error deleting product: ${err.message}`;
        this.successMessage = '';
      }
    });
  }


}
