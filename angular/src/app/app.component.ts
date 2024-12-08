import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'customerops';

  showButtons: boolean = true;

  constructor(private router : Router){
    this.router.events.subscribe(() => {
      this.showButtons = this.router.url === '/';
    });
  }

  goToCustomer(){
    this.router.navigate(["customer"])
  }

  goToAdmin(){
    this.router.navigate(["admin"])
  }
}
