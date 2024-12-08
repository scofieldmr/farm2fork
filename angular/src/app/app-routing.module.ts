import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { CustomerComponent } from './customer/customer.component';
import { ProductsComponent } from './customer/products/products.component';
import { AddUserComponent } from './add-user/add-user.component';
import { CartComponent } from './customer/cart/cart.component';
import { WishlistComponent } from './customer/wishlist/wishlist.component';
import { OrderhistoryComponent } from './customer/orderhistory/orderhistory.component';
import { MyProfileComponent } from './customer/my-profile/my-profile.component';
import { AdminComponent } from './admin/admin.component';
import { ManageUsersComponent } from './admin/manage-users/manage-users.component';
import { ManageProductsComponent } from './admin/manage-products/manage-products.component';
import { FilterByDomainComponent } from './admin/manage-users/filter-by-domain/filter-by-domain.component';
import { DomainCountComponent } from './admin/manage-users/domain-count/domain-count.component';
import { ViewAllCustomerComponent } from './admin/manage-users/view-all-customer/view-all-customer.component';
import { AuthGuard } from './auth.guard';
import { UpdateAdminProfileComponent } from './admin/update-admin-profile/update-admin-profile.component';
import { AddProductComponent } from './admin/manage-products/add-product/add-product.component';
import { UpdateProductComponent } from './admin/manage-products/update-product/update-product.component';
import { DeleteProductComponent } from './admin/manage-products/delete-product/delete-product.component';
import { DisplayProductsComponent } from './admin/manage-products/display-products/display-products.component';
import { BulkUploadProductsComponent } from './admin/manage-products/bulk-upload-products/bulk-upload-products.component';
import { SearchProductByNameComponent } from './customer/search-product-by-name/search-product-by-name.component';
import { DemandProductComponent } from './admin/manage-products/demand-product/demand-product.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: AddUserComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }, 

  {
    path: 'customer',
    component: CustomerComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'products', component: ProductsComponent },
      { path: 'search', component: SearchProductByNameComponent},
      { path: 'cart', component: CartComponent },
      { path: 'wishlist', component: WishlistComponent },
      { path: 'order-history', component: OrderhistoryComponent },
      { path: 'my-profile', component: MyProfileComponent },
      { path: '', redirectTo: 'products', pathMatch: 'full' } 
    ]
  },

  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard], 
    children: [
      { path: 'manage-users', component: ManageUsersComponent },
      { path: 'manage-products', component: ManageProductsComponent },
      { path: 'update-profile', component: UpdateAdminProfileComponent},
      { path: 'filter-by-domain', component: FilterByDomainComponent },
      { path: 'domain-count', component: DomainCountComponent },
      { path: 'view-all-customers', component: ViewAllCustomerComponent },
      { path: 'add-product', component: AddProductComponent},
      { path: 'update-product', component: UpdateProductComponent},
      { path: 'delete-product', component: DeleteProductComponent},
      { path: 'view-products', component: DisplayProductsComponent},
      { path: 'bulk-upload', component: BulkUploadProductsComponent},
      { path: 'products-in-demand', component: DemandProductComponent},
      { path: '', redirectTo: 'manage-users', pathMatch: 'full' } 
    ]
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
