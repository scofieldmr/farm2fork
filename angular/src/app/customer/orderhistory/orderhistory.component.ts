import { Component, OnInit } from '@angular/core';
import { OrderHistory } from '../../orderhistory.model';
import { CustomerService } from '../../customer-service.service';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-orderhistory',
  templateUrl: './orderhistory.component.html',
  styleUrl: './orderhistory.component.css'
})
export class OrderhistoryComponent implements OnInit{

  customerId  !: number | null;

  orderHistory !: OrderHistory[];

  constructor(private customerService : CustomerService, private authService: AuthService){

  }

  ngOnInit(): void {
      this.customerId = this.authService.getUserId();
      this.loadOrderHistory(this.customerId);
  }

  loadOrderHistory(customerId : number | null){
    this.customerService.fetchOrderHistory(customerId).subscribe(
      (data) => {this.orderHistory =  data},
      (error) => console.log(error)
    );
  }

}
