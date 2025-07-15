import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Caroption } from '../models/caroption';
import { environment } from '../../environments/environment';


@Injectable({ providedIn: 'root' })
export class CaroptionService {
  private baseUrl = `${environment.apiUrl}/api/caroption`;

  constructor(private http: HttpClient) { }

  getAllOptions(): Observable<Caroption[]> {
    return this.http.get<Caroption[]>(this.baseUrl);
  }


  getOptionById(id: string): Observable<Caroption> {
    return this.http.get<Caroption>(`${this.baseUrl}/${id}`);
  }
}
