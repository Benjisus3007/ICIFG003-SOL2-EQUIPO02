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
    if (formulario.invalid || this.contacto.mensaje.trim().length < 20) {
      this.mostrarMensaje('Completa nombre, correo válido y un mensaje de al menos 20 caracteres.', 'error');
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
