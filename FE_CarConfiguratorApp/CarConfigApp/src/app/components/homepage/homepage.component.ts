import { Component, OnInit } from '@angular/core';
import { Car } from '../../models/car';
import { Caroption } from '../../models/caroption';
import { Configuration } from '../../models/configuration';
import { CarService } from '../../services/car.service';
import { CaroptionService } from '../../services/caroption.service';
import { ConfigurationService } from '../../services/configuration.service';
import { KeycloakOperationService } from '../../services/keycloak.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { v4 as uuidv4 } from 'uuid';
import { Router } from '@angular/router';

import { HeaderComponent } from '../shared/header/header.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatSnackBarModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule,
    MatFormFieldModule,
    HeaderComponent
  ],
})
export class HomepageComponent implements OnInit {
  cars: Car[] = [];
  caroptions: Caroption[] = [];
  selectedCar: Car | null = null;
  selectedCarId: string = '';
  selectedOptionIds: string[] = [];
  userId: string = '';
  totalPrice: number = 0;
  configurationURL: string | null = null;
  selectedOptions: { [type: string]: string } = {};
  optionTypes: string[] = ['Color', 'Engine', 'Rim', 'Special Equipment',];

  constructor(
    private carService: CarService,
    private optionService: CaroptionService,
    private configService: ConfigurationService,
    private keycloakService: KeycloakOperationService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadCars();
    this.loadOptions();
    this.loadUserProfile();
  }

  loadCars() {
    this.carService.getAllCars().subscribe({
      next: (cars:any) => (this.cars = cars),
      error: () => this.showError('Fehler beim Laden der Autos'),
    });
  }

  loadOptions() {
    this.optionService.getAllOptions().subscribe({
      next: (opts) => {
        console.log('Loaded caroptions:', opts);
        this.caroptions = opts;
      },
      error: () => this.showError('Fehler beim Laden der Optionen'),
    });
  }


  loadUserProfile() {
    this.keycloakService.getUserProfile().then((profile: any) => {
      this.userId = profile.id || profile.sub;
    });

  }

  onCarChange() {
    this.selectedCar = this.cars.find((car) => car.id === this.selectedCarId) || null;
    this.updateTotalPrice();
  }

  onOptionSelect(type: string, optionId: string) {
    if (optionId) {
      this.selectedOptions[type] = optionId;
    } else {
      delete this.selectedOptions[type];
    }
    this.updateTotalPrice();
  }

  getOptionsByType(type: string): Caroption[] {
    return this.caroptions.filter(o => o.option_type === type);
  }


  onOptionChange(event: Event, optionId: string): void {
    const input = event.target as HTMLInputElement;
    const checked = input?.checked ?? false;
    this.onOptionToggle(optionId, checked);
  }


  onOptionToggle(optionId: string, checked: boolean) {
    if (checked) {
      this.selectedOptionIds.push(optionId);
    } else {
      this.selectedOptionIds = this.selectedOptionIds.filter((id) => id !== optionId);
    }
    this.updateTotalPrice();
  }

  getOptionsTotal(): number {
    return Object.values(this.selectedOptions)
      .map(id => this.caroptions.find(o => o.id === id))
      .filter((o): o is Caroption => !!o)
      .reduce((sum, o) => sum + o.additional_price, 0);
  }

  getSelectedOptions(): Caroption[] {
    return Object.values(this.selectedOptions)
      .map(id => this.caroptions.find(o => o.id === id))
      .filter((o): o is Caroption => !!o);
  }

  updateTotalPrice() {
    const optionsSum = Object.values(this.selectedOptions)
      .map(selectedId => this.caroptions.find(o => o.id === selectedId))
      .filter(o => !!o)
      .reduce((sum, o) => sum + (o?.additional_price ?? 0), 0);
    this.totalPrice = (this.selectedCar?.basePrice || 0) + optionsSum;
  }

  createConfiguration() {
    if (!this.selectedCar) {
      this.showError('Bitte ein Auto auswählen.');
      return;
    }

    const config: Configuration = {
      id: uuidv4(),
      userId: this.userId,
      carId: this.selectedCar.id,
      selectedOptionIds: Object.values(this.selectedOptions), // falls du diese Logik nutzt
      totalPrice: this.totalPrice,
      createdAt: new Date().toISOString(),
      ordered: false
    };

    this.configService.saveConfiguration(config).subscribe({
      next: () => {
        this.snackBar.open('Konfiguration gespeichert!', 'Schließen', { duration: 3000 });
        // Automatisch zur Detail-Seite gehen:
        this.router.navigate(['/configuration', config.id]);
      },
      error: () => {
        this.showError('Fehler beim Speichern');
      }
    });
  }


  
  logout() {
    this.keycloakService.logout();
  }

  showError(message: string) {
    this.snackBar.open(message, 'Schließen', { duration: 4000 });
  }
}
