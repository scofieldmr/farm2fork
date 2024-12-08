import { Component } from '@angular/core';
import { ManageProductService } from '../../../manage-product.service';
import { HttpClient } from '@angular/common/http';
import * as Papa from 'papaparse';

@Component({
  selector: 'app-bulk-upload-products',
  templateUrl: './bulk-upload-products.component.html',
  styleUrl: './bulk-upload-products.component.css'
})
export class BulkUploadProductsComponent {

  constructor(private manageProductService: ManageProductService){}

  products: any[] = [];
  isParsed: boolean = false;
  message: string = '';


  successMessage : string = '';
  errorMessage: string = '';


  onFileSelect(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.parseCSV(file);
    }
  }

  parseCSV(file: File) {
    Papa.parse(file, {
      header: true,
      skipEmptyLines: true,
      complete: (result) => {
        this.products = result.data.map((data: any) => ({
          productName: data['productName'],
          productCategory: data['productCategory'],
          quantity: +data['quantity'],
          price: +data['price']
        }));
        this.isParsed = true;
        this.message = 'CSV parsed successfully. Check the result below.';
      },
      error: (error) => {
        console.error('Error parsing CSV:', error);
        this.message = 'Error parsing CSV. Please try again.';
      }
    });
  }

  cancelProduct(index: number) {
    this.products.splice(index, 1); 
    this.message = 'Product canceled from upload list.';
  }

  addAllProducts() {
    console.log(this.products)
    this.manageProductService.addAllProducts(this.products).subscribe({
      next: (response) => {
        this.successMessage = response;
        this.products = [];
      },
      error: () => {
        this.errorMessage = 'Error adding all products.';
      }
    });
  }


}
