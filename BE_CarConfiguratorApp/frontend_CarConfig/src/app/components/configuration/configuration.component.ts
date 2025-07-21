import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from '../../models/car';
import { Caroption } from '../../models/caroption';
import { Configuration } from '../../models/configuration';
import { CarService } from '../../services/car.service';
import { CaroptionService } from '../../services/caroption.service';
import { ConfigurationService } from '../../services/configuration.service';
import { KeycloakOperationService } from '../../services/keycloak.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { HeaderComponent } from '../shared/header/header.component';
import { OrderService, OrderRequest } from '../../services/order.service';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-configuration',
  standalone: true,
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css'],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    HeaderComponent
  ],
})
export class ConfigurationComponent implements OnInit {
  configuration: Configuration | null = null;
  car: Car | null = null;
  carOptions: Caroption[] = [];
  selectedOptions: Caroption[] = [];
  userProfile: { userid: string; username: string; email: string } | null = null;

  constructor(
    private route: ActivatedRoute,
    private configService: ConfigurationService,
    private carService: CarService,
    private optionService: CaroptionService,
    private keycloakService: KeycloakOperationService,
    private snackBar: MatSnackBar,
    private orderService: OrderService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const configId = this.route.snapshot.paramMap.get('id');
    if (!configId) {
      this.showError('Keine Konfigurations-ID angegeben.');
      return;
    }

    this.configService.getConfigurationById(configId).subscribe({
      next: (conf) => {
        this.configuration = conf;
        this.loadCar();
        this.loadOptions();
        this.loadUserProfile();
      },
      error: () => this.showError('Fehler beim Laden der Konfiguration.')
    });
  }

  loadCar() {
    if (!this.configuration) return;
    this.carService.getCarById(this.configuration.carId).subscribe({
      next: (car) => (this.car = car),
      error: () => this.showError('Fehler beim Laden des Autos.')
    });
  }

  loadOptions() {
    if (!this.configuration) return;
    this.optionService.getAllOptions().subscribe({
      next: (opts) => {
        this.carOptions = opts;
        if (this.configuration?.selectedOptionIds?.length) {
          this.selectedOptions = this.carOptions.filter(opt =>
            this.configuration!.selectedOptionIds!.includes(opt.id)
          );
        } else {
          this.selectedOptions = [];
        }
      },
      error: () => this.showError('Fehler beim Laden der Optionen.')
    });
  }

  loadUserProfile() {
    if (this.keycloakService.isLoggedIn()) {
      this.keycloakService.getUserProfile().then((profile: any) => {
        console.log('Profile:', profile);
        this.userProfile = {
          userid: profile.id, 
          username: profile.username,
          email: profile.email
        };
        console.log('UserId gespeichert:', this.userProfile.userid);
      });
    } else {
      console.warn('Benutzer ist nicht eingeloggt.');
    }
  }

  getOptionsTotal(): number {
    return this.selectedOptions.reduce((sum, o) => sum + o.additional_price, 0);
  }

  getTotalPrice(): number {
    return (this.car?.basePrice ?? 0) + this.getOptionsTotal();
  }

  orderNow() {
    if (!this.configuration) {
      this.showError('Keine Konfiguration geladen.');
      return;
    }
    if (!this.userProfile || !this.userProfile.userid) {
      this.showError('Benutzerprofil nicht geladen. Bitte Seite aktualisieren.');
      return;
    }

    const order: OrderRequest = {
      id: uuidv4(),
      userId: this.userProfile.userid,
      configurationId: this.configuration.id,
      finalPrice: this.getTotalPrice()
    };

    console.log('Order vor dem Senden:', order);

    this.orderService.placeOrder(order).subscribe({
      next: (orderId: string) => {
        // Konfiguration als bestellt markieren
        const updatedConfig: Configuration = {
          ...this.configuration!,
          ordered: true
        };

        this.configService.updateConfiguration(updatedConfig).subscribe({
          next: () => {
            this.snackBar.open('Bestellung erfolgreich aufgegeben!', 'Schließen', { duration: 3000 });

            // Navigiere auf Bestelldetailseite
            console.log("OrderId aus Backend:", orderId);
            this.router.navigate(['/order', orderId]);
          },
          error: () => {
            this.showError('Fehler beim Aktualisieren des Status.');
          }
        });
      },
      error: () => {
        this.showError('Fehler beim Erstellen der Bestellung.');
      }
    });
  }

  logout() {
    this.keycloakService.logout();
  }

  showError(message: string) {
    this.snackBar.open(message, 'Schließen', { duration: 5000 });
  }
}
