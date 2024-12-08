import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-products',
  templateUrl: './manage-products.component.html',
  styleUrl: './manage-products.component.css'
})
export class ManageProductsComponent {

  constructor(private router: Router){}

  addProduct(){
    this.router.navigate(['/admin/add-product'])
  }

  updateProduct(){
    this.router.navigate(['/admin/update-product'])
  }

  deleteProduct(){
    this.router.navigate(['/admin/delete-product'])
  }

  viewProducts(){
    this.router.navigate(['/admin/view-products'])
  }

  bulkUpload(){
    this.router.navigate(['/admin/bulk-upload'])
  }

  productsInDemand(){
    this.router.navigate(["/admin/products-in-demand"])
  }



}
