import { Component, inject, signal } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MenuComponent } from '../../components/menu/menu.component';
import { Mensaje } from '../../components/mensaje/mensaje.component';
import { Contacto } from '../../models/petshop.models';
import { CarritoService } from '../../services/carrito.service';
import { ContactoService } from '../../services/contacto.service';

@Component({
  selector: 'app-contacto-page',
  imports: [FormsModule, MenuComponent, Mensaje],
  templateUrl: './contacto-page.component.html',
  styleUrl: './contacto-page.component.css',
})
export class ContactoPageComponent {
  private readonly contactoService = inject(ContactoService);
  readonly carritoService = inject(CarritoService);

  mensaje = signal('');
  tipoMensaje = signal<'exito' | 'error' | 'info'>('info');

  contacto: Contacto = {
    nombre: '',
    correo: '',
    mensaje: '',
  };

enviarContacto(formulario: NgForm): void {
  const errores: string[] = [];

  if (!this.contacto.nombre.trim()) {
    errores.push('El nombre es obligatorio.');
  }

  const correoValido = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.contacto.correo);
  if (!this.contacto.correo.trim()) {
    errores.push('El correo electrónico es obligatorio.');
  } else if (!correoValido) {
    errores.push('El correo electrónico no tiene un formato válido.');
  }

  if (!this.contacto.mensaje.trim()) {
    errores.push('El mensaje es obligatorio.');
  } else if (this.contacto.mensaje.trim().length < 20) {
    errores.push('El mensaje debe tener al menos 20 caracteres.');
  }

  if (errores.length > 0) {
    this.mostrarMensaje(errores.join(' '), 'error');
    return;
  }

  this.contactoService.enviar(this.contacto).subscribe({
    next: () => {
      this.mostrarMensaje('Mensaje enviado correctamente. Pronto nos pondremos en contacto.', 'exito');
      formulario.resetForm();
      this.contacto = { nombre: '', correo: '', mensaje: '' };
    },
    error: () => this.mostrarMensaje('No se pudo enviar el mensaje. Intenta nuevamente.', 'error'),
  });
}

  private mostrarMensaje(texto: string, tipo: 'exito' | 'error' | 'info'): void {
    this.mensaje.set(texto);
    this.tipoMensaje.set(tipo);
    window.setTimeout(() => this.mensaje.set(''), 4500);
  }
}
