import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../shared/header/header.component';
import { RouterModule } from '@angular/router';
import { KeycloakOperationService } from '../../services/keycloak.service';

@Component({
  selector: 'app-userorder',
  standalone: true,
  templateUrl: './userorder.component.html',
  styleUrls: ['./userorder.component.css'],
  imports: [CommonModule, HeaderComponent, RouterModule]
})
export class UserOrdersComponent implements OnInit {
  orders: any[] = [];

  constructor(
    private orderService: OrderService,
    private keycloakService: KeycloakOperationService
  ) { }

  ngOnInit(): void {
    this.keycloakService.getUserProfile().then((profile: any) => {
      const userId = profile?.id || profile?.sub;
      if (!userId) {
        console.error('Keine UserID gefunden.');
        return;
      }

      this.orderService.getOrdersByUser(userId).subscribe({
        next: (data: any) => (this.orders = data),
        error: () => console.error('Fehler beim Laden der Bestellungen')
      });
    });
  }
}
