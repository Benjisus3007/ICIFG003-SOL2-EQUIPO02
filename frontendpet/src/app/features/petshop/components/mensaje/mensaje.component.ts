import { Component, input } from '@angular/core';

@Component({
  selector: 'app-mensaje',
  imports: [],
  templateUrl: './mensaje.component.html',
  styleUrl: './mensaje.component.css',
})
export class Mensaje {
  tipo = input<'exito' | 'error' | 'info'>('info');
  texto = input<string>('');
}
