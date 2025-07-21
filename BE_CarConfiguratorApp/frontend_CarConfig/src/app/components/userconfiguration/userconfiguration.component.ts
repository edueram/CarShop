import { Component, OnInit } from '@angular/core';
import { ConfigurationService } from '../../services/configuration.service';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../shared/header/header.component';
import { RouterModule } from '@angular/router';
import { KeycloakOperationService } from '../../services/keycloak.service';

@Component({
  selector: 'app-userconfigurations',
  standalone: true,
  templateUrl: './userconfiguration.component.html',
  styleUrls: ['./userconfiguration.component.css'],
  imports: [CommonModule, HeaderComponent, RouterModule]
})
export class UserConfigurationComponent implements OnInit {
  configurations: any[] = [];

  constructor(private configService: ConfigurationService,
    private keycloakService: KeycloakOperationService) { }

  ngOnInit(): void {
    this.keycloakService.getUserProfile().then((profile: any) => {
      const userId = profile?.id || profile?.sub;
      if (!userId) {
        console.error('Keine UserID gefunden.');
        return;
      }
      this.configService.getAllUserConfigurations(userId).subscribe({
        next: (data: any) => (this.configurations = data),
        error: () => console.error('Fehler beim Laden der Konfigurationen')
      });
    });
  }
}
