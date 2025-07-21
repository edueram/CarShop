import { Component, OnInit } from '@angular/core';
import { KeycloakOperationService } from '../../services/keycloak.service';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../shared/header/header.component';
import { RouterModule } from '@angular/router'; 

@Component({
  selector: 'app-userprofile',
  standalone: true,
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css'],
  imports: [CommonModule, HeaderComponent, RouterModule]
})
export class UserProfileComponent implements OnInit {
  profile: any = null;

  constructor(private keycloak: KeycloakOperationService) { }

  ngOnInit(): void {
    this.keycloak.getUserProfile().then((data:any) => {
      this.profile = data;
    });
  }
}
