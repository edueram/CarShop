import { Routes } from '@angular/router';
import { HomepageComponent } from './components/homepage/homepage.component';
import { ConfigurationComponent } from './components/configuration/configuration.component';
import { AuthGuard }  from './guard/auth.guard';
import { UserProfileComponent }  from './components/userprofile/userprofile.component';
import { UserConfigurationComponent } from './components/userconfiguration/userconfiguration.component';
import { UserOrdersComponent } from './components/userorder/userorder.component';
import { OrderDetailComponent } from './components/orderdetail/orderdetail.component';

export const routes: Routes = [
  { path: '', component: HomepageComponent, canActivate: [AuthGuard] },
  { path: 'configuration/:id', component: ConfigurationComponent },
  { path: 'myprofile', component: UserProfileComponent },
  { path: 'myconfigurations', component: UserConfigurationComponent },
  { path: 'myorders', component: UserOrdersComponent },
  { path: 'order/:orderId', component: OrderDetailComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' } 
];
