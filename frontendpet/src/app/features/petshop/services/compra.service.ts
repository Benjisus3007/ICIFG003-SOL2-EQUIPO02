import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompraResponseDTO, CrearCompraRequest } from '../models/petshop.models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompraService {
  private apiUrl = 'http://localhost:8080/api/compras';

  constructor(private http: HttpClient) {}

  confirmarCompra(data: CrearCompraRequest): Observable<CompraResponseDTO> {
    return this.http.post<CompraResponseDTO>(`${this.apiUrl}/confirmar`, data);
  }
}