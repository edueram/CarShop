import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Configuration } from '../models/configuration';
import { environment } from '../../environments/environment';


@Injectable({ providedIn: 'root' })
export class ConfigurationService {
  private apiUrl = `${environment.apiUrl}/api/configuration`;

  constructor(private http: HttpClient) {}

  saveConfiguration(config: Configuration): Observable<Configuration> {
    return this.http.post<Configuration>(this.apiUrl, config);
  }

  getConfigurationById(id: string): Observable<Configuration> {
    return this.http.get<Configuration>(`${this.apiUrl}/${id}`);
  }

  getAllUserConfigurations(userId: string): Observable<Configuration[]> {
    return this.http.get<Configuration[]>(`${this.apiUrl}/user/${userId}`);
  }
  updateConfiguration(config: Configuration): Observable<Configuration> {
    return this.http.put<Configuration>(this.apiUrl, config);
  }

}
