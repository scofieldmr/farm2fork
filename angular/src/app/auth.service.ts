// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {} 

  private loginUrl = 'http://localhost:8070/grocery/login';

  private isLoggedIn = false;

  login(credentials: any): Observable<any> {
    return this.http.post(this.loginUrl, credentials).pipe(
      tap((response: any) => {
        if (response && response.userId) {
          this.isLoggedIn = true;
          localStorage.setItem('userId', response.userId); 
        }
      })
    );
  }

  getUserId(): number | null {
    const userId = localStorage.getItem('userId');
    return userId ? parseInt(userId, 10) : null;
  }
  

  logout(): void {
    localStorage.removeItem('userId');
    this.isLoggedIn = false
  }

  isAuthenticated(): boolean {
    return this.isLoggedIn;
  }
}
