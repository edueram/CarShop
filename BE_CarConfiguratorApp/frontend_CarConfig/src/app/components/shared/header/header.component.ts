import { Component } from '@angular/core';
import { KeycloakOperationService } from '../../../services/keycloak.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  standalone: true,
  imports: [CommonModule, RouterModule],
})
export class HeaderComponent {
  profileDropdownOpen = false;
  username = 'Profil';
  email = 'email';
  fullName = 'fullname';

  constructor(private keycloak: KeycloakOperationService) {
    this.keycloak.getUserProfile().then((profile: any) => {
      console.log(profile);
      this.username = profile?.username || 'Profil';
      this.email = profile?.email;
      this.fullName = profile?.firstName + ' ' + profile?.lastName;
    });
  }

  toggleDropdown() {
    this.profileDropdownOpen = !this.profileDropdownOpen;
  }

  logout() {
    this.keycloak.logout(window.location.origin);
  }
}
