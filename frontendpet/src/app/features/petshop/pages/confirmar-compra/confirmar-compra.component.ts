import { CurrencyPipe, DatePipe } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MenuComponent } from '../../components/menu/menu.component';
import { Mensaje } from '../../components/mensaje/mensaje.component';
import { ClienteCompra, CrearCompraRequest } from '../../models/petshop.models';
import { CarritoService } from '../../services/carrito.service';
import { CompraService } from '../../services/compra.service';

@Component({
  selector: 'app-confirmar-compra',
  imports: [FormsModule, CurrencyPipe, DatePipe, RouterLink, MenuComponent, Mensaje],
  templateUrl: './confirmar-compra.component.html',
  styleUrl: './confirmar-compra.component.css',
})
export class ConfirmarCompraComponent {
  readonly carritoService = inject(CarritoService);
  private readonly compraService = inject(CompraService);
  private readonly router = inject(Router);

  fechaActual = new Date();

  mensaje = signal('');
  tipoMensaje = signal<'exito' | 'error' | 'info'>('info');
  guardando = signal(false);

  cliente: ClienteCompra = {
    rut: '',
    nombre: '',
    apellido: '',
    correo: '',
    telefono: '',
    direccion: '',
  };

  observaciones = '';

  confirmarCompra(formulario: NgForm): void {
    if (this.carritoService.productosCarrito().length === 0) {
      this.mostrarMensaje('No puedes confirmar una compra con el carrito vacío.', 'error');
      return;
    }

    if (
      formulario.invalid ||
      !this.cliente.nombre.trim() ||
      !this.cliente.apellido.trim() ||
      !this.cliente.correo.trim() ||
      !this.cliente.telefono.trim()
    ) {
      this.mostrarMensaje('Completa todos los datos obligatorios del cliente.', 'error');
      return;
    }

    const request: CrearCompraRequest = {
      cliente: {
        rut: this.cliente.rut.trim(),
        nombre: this.cliente.nombre.trim(),
        apellido: this.cliente.apellido.trim(),
        correo: this.cliente.correo.trim(),
        telefono: this.cliente.telefono.trim(),
        direccion: this.cliente.direccion.trim(),
      },
      productos: this.carritoService.productosParaCompra(),
      tipoRetiro: 'RETIRO_EN_TIENDA',
      observaciones: this.observaciones.trim(),
    };

    this.guardando.set(true);

    this.compraService.confirmarCompra(request).subscribe({
      next: (respuesta) => {
        this.guardando.set(false);
        this.carritoService.vaciarCarrito();

        this.mostrarMensaje(
          `${respuesta.mensaje} Código de compra: ${respuesta.idCompra}`,
          'exito'
        );

        formulario.resetForm();
        this.observaciones = '';
      },
      error: () => {
        this.guardando.set(false);
        this.mostrarMensaje('No se pudo confirmar la compra. Revisa el stock o intenta nuevamente.', 'error');
      }
    });
  }

  volverAlCatalogo(): void {
    this.router.navigate(['/catalogo']);
  }

  private mostrarMensaje(texto: string, tipo: 'exito' | 'error' | 'info'): void {
    this.mensaje.set(texto);
    this.tipoMensaje.set(tipo);
    window.setTimeout(() => this.mensaje.set(''), 6000);
  }
}