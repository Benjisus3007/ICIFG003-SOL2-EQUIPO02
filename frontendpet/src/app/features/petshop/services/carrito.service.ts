import { HttpClient } from '@angular/common/http';
import { Injectable, computed, signal } from '@angular/core';
import {
  AgregarAlCarritoRequest,
  CarritoResponseDTO,
  ItemCarritoAgrupado,
  Producto,
  ProductoCompra
} from '../models/petshop.models';

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

  itemsAgrupados = computed<ItemCarritoAgrupado[]>(() => {
    const mapa = new Map<number, ItemCarritoAgrupado>();

    for (const producto of this.productosCarrito()) {
      const itemExistente = mapa.get(producto.id);

      if (itemExistente) {
        itemExistente.cantidad += 1;
        itemExistente.subtotal = itemExistente.cantidad * itemExistente.producto.precio;
      } else {
        mapa.set(producto.id, {
          producto,
          cantidad: 1,
          subtotal: producto.precio,
        });
      }
    }

    return Array.from(mapa.values());
  });

  constructor(private http: HttpClient) {}

  agregarLocal(producto: Producto): void {
    this.productosCarrito.update(productos => [...productos, producto]);
  }

  agregarEnBackend(data: AgregarAlCarritoRequest) {
    return this.http.post<any>(`${this.apiUrl}/agregar`, data);
  }

  eliminarProducto(idProducto: number): void {
    const copia = [...this.productosCarrito()];
    const indice = copia.findIndex(producto => producto.id === idProducto);

    if (indice >= 0) {
      copia.splice(indice, 1);
      this.productosCarrito.set(copia);
    }
  }

  vaciarCarrito(): void {
    this.productosCarrito.set([]);
  }

  productosParaCompra(): ProductoCompra[] {
    return this.itemsAgrupados().map(item => ({
      idProducto: item.producto.id,
      cantidad: item.cantidad,
    }));
  }
}