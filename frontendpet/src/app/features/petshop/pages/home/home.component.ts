import { CurrencyPipe } from '@angular/common';
import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

import { MenuComponent } from '../../components/menu/menu.component';
import { Producto } from '../../models/petshop.models';
import { CarritoService } from '../../services/carrito.service';
import { ProductoService } from '../../services/producto.service';

@Component({
  selector: 'app-home',
  imports: [RouterLink, MenuComponent, CurrencyPipe],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  readonly carritoService = inject(CarritoService);
  private readonly productoService = inject(ProductoService);

  productos = signal<Producto[]>([]);

  productosDestacados = computed(() => this.productos().slice(0, 3));

  ngOnInit(): void {
    this.productoService.listarConStock().subscribe({
      next: (productos) => {
        this.productos.set(productos);
      },
      error: (error) => {
        console.error('Error al cargar productos destacados', error);
      },
    });
  }
}