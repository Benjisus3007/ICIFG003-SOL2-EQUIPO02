import { HttpClient } from '@angular/common/http';
import { Injectable, computed, inject, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { AgregarAlCarritoRequest, CarritoResponseDTO, ItemCarritoLocal, Producto } from '../models/petshop.models';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private apiUrl = 'http://localhost:8080/api/carritos';

  productosCarrito = signal<Producto[]>([]);

  cantidad = computed(() => this.productosCarrito().length);

  total = computed(() =>
    this.productosCarrito().reduce((suma, producto) => suma + producto.precio, 0)
  );

  constructor(private http: HttpClient) {}

  agregarLocal(producto: Producto): void {
    this.productosCarrito.update(productos => [...productos, producto]);
  }

  agregarEnBackend(data: {
    idCliente: number;
    idProducto: number;
    cantidad: number;
  }) {
    return this.http.post(`${this.apiUrl}/agregar`, data);
  }

  eliminarProducto(idProducto: number): void {
    this.productosCarrito.update(productos =>
      productos.filter(producto => producto.id !== idProducto)
    );
  }

  vaciarCarrito(): void {
    this.productosCarrito.set([]);
  }
}