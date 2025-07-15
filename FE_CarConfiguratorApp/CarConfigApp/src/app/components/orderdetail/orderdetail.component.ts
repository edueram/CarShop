import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService, OrderResponse } from '../../services/order.service';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../shared/header/header.component';

import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-order-detail',
  standalone: true,
  templateUrl: './orderdetail.component.html',
  styleUrls: ['./orderdetail.component.css'],
  imports: [CommonModule, HeaderComponent, RouterModule]
})
export class OrderDetailComponent implements OnInit {
  order: OrderResponse | null = null;

  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    const orderId = this.route.snapshot.paramMap.get('orderId');
    if (orderId) {
      this.orderService.getOrderById(orderId).subscribe({
        next: (data) => (this.order = data),
        error: () => console.error('Fehler beim Laden der Bestellung')
      });
    }
  }
}
