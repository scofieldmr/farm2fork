import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerComponent } from './customer/customer.component';
import { ProductsComponent } from './customer/products/products.component';
import { CartComponent } from './customer/cart/cart.component';
import { WishlistComponent } from './customer/wishlist/wishlist.component';
import { OrderhistoryComponent } from './customer/orderhistory/orderhistory.component';
import { MyProfileComponent } from './customer/my-profile/my-profile.component';
import { AdminComponent } from './admin/admin.component';
import { ManageUsersComponent } from './admin/manage-users/manage-users.component';
import { ManageProductsComponent } from './admin/manage-products/manage-products.component';
import { FilterByDomainComponent } from './admin/manage-users/filter-by-domain/filter-by-domain.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AddUserComponent } from './add-user/add-user.component';
import { DomainCountComponent } from './admin/manage-users/domain-count/domain-count.component';
import { ViewAllCustomerComponent } from './admin/manage-users/view-all-customer/view-all-customer.component';
import { BulkUploadProductsComponent } from './admin/manage-products/bulk-upload-products/bulk-upload-products.component';
import { AuthGuard } from './auth.guard';
import { UpdateAdminProfileComponent } from './admin/update-admin-profile/update-admin-profile.component';
import { AddProductComponent } from './admin/manage-products/add-product/add-product.component';
import { DeleteProductComponent } from './admin/manage-products/delete-product/delete-product.component';
import { DisplayProductsComponent } from './admin/manage-products/display-products/display-products.component';
import { UpdateProductComponent } from './admin/manage-products/update-product/update-product.component';
import { SearchProductByNameComponent } from './customer/search-product-by-name/search-product-by-name.component';
import { DemandProductComponent } from './admin/manage-products/demand-product/demand-product.component';



@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    ProductsComponent,
    CartComponent,
    WishlistComponent,
    OrderhistoryComponent,
    MyProfileComponent,
    AdminComponent,
    ManageUsersComponent,
    ManageProductsComponent,
    FilterByDomainComponent,
    LoginComponent,
    AddUserComponent,
    DomainCountComponent,
    ViewAllCustomerComponent,
    BulkUploadProductsComponent,
    UpdateAdminProfileComponent,
    AddProductComponent,
    DeleteProductComponent,
    DisplayProductsComponent,
    UpdateProductComponent,
    SearchProductByNameComponent,
    DemandProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule, 
    ReactiveFormsModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
