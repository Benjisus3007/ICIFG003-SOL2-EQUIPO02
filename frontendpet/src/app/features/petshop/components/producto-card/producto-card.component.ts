import { CurrencyPipe } from '@angular/common';
import { Component, input, output } from '@angular/core';
import { Producto } from '../../models/petshop.models';

@Component({
  selector: 'app-producto-card',
  imports: [CurrencyPipe],
  templateUrl: './producto-card.component.html',
  styleUrl: './producto-card.component.css',
})
export class ProductoCard {
  producto = input.required<Producto>();
  agregar = output<Producto>();

  agregarAlCarrito(): void {
    this.agregar.emit(this.producto());
  }
}
