import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface OrderRequest {
  id: string;
  userId: string;
  configurationId: string;
  finalPrice: number;
}

export interface OrderResponse {
  id: string;
  userId: string;
  configurationId: string;
  orderDate: string;
  finalPrice: number;
  status: string;
}

@Injectable({ providedIn: 'root' })
export class OrderService {
  private apiUrl = `${environment.apiUrl}/api/order`;

  constructor(private http: HttpClient) { }

  placeOrder(order: OrderRequest): Observable<string> {
    return this.http.post(this.apiUrl, order, { responseType: 'text' });
  }

  getOrdersByUser(userId: string): Observable<OrderResponse[]> {
    return this.http.get<OrderResponse[]>(`${this.apiUrl}/user/${userId}`);
  }

  getOrderById(orderId: string): Observable<OrderResponse> {
    return this.http.get<OrderResponse>(`${this.apiUrl}/${orderId}`);
  }


  getOrderByConfig(configId: string): Observable<OrderResponse> {
    return this.http.get<OrderResponse>(`${this.apiUrl}/configuration/${configId}`);
  }
}
