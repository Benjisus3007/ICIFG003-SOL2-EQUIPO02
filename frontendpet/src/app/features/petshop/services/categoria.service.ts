import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoriaProducto } from '../models/petshop.models';

@Injectable({ providedIn: 'root' })
export class CategoriaService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/categorias';

  listarTodas(): Observable<CategoriaProducto[]> {
    return this.http.get<CategoriaProducto[]>(this.apiUrl);
  }
}
