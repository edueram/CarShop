import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/shared/header/header.component';
import { HomepageComponent } from './components/homepage/homepage.component'; 

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ RouterOutlet], 
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent {
  protected title = 'CarConfigApp';
}
