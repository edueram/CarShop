import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from '../models/car';
import { environment } from '../../environments/environment';


@Injectable({ providedIn: 'root' })
export class CarService {
  private baseUrl = `${environment.apiUrl}/api/car`;

  constructor(private http: HttpClient) {}

  getAllCars(): Observable<Car[]> {
    return this.http.get<Car[]>(this.baseUrl);
  }

  getCarById(id: string): Observable<Car> {
    return this.http.get<Car>(`${this.baseUrl}/${id}`);
  }
}
