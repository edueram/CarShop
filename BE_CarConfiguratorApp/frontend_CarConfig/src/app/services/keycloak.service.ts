import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { UserProfile } from '../models/userprofile';


@Injectable({ providedIn: 'root' })
export class KeycloakOperationService {
  constructor(private readonly keycloak: KeycloakService) { }

  isLoggedIn(): boolean {
    return this.keycloak.isLoggedIn();
  }
  logout(redirectUri?: string) {
    return this.keycloak.logout(redirectUri);
  }
  getUserProfile(): any {
    return this.keycloak.loadUserProfile();
  }


  getUserProInfos(): Promise<UserProfile> {
    return this.keycloak.loadUserProfile().then(profile => ({
      id: profile.id ?? '',
      username: profile.username !== undefined ? profile.username : 'Unbekannt',
      email: profile.email !== undefined ? profile.email : ''
    }));
  }


  // Add other methods as needed for token access, user info retrieval, etc.}
}
