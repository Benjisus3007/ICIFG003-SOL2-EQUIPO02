import { CurrencyPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuComponent } from '../../components/menu/menu.component';
import { CarritoService } from '../../services/carrito.service';

@Component({
  selector: 'app-carrito-page',
  imports: [CurrencyPipe, RouterLink, MenuComponent],
  templateUrl: './carrito-page.component.html',
  styleUrl: './carrito-page.component.css',
})
export class CarritoPageComponent {
  readonly carritoService = inject(CarritoService);

  eliminarProducto(idProducto: number): void {
    this.carritoService.eliminarProducto(idProducto);
  }

  vaciarCarrito(): void {
    this.carritoService.vaciarCarrito();
  }
}