import { Routes } from '@angular/router';
import { HomeComponent } from './features/petshop/pages/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },

  {
    path: 'catalogo',
    loadChildren: () =>
      import('./features/petshop/catalogo.routes').then(m => m.CATALOGO_ROUTES)
  },

  {
    path: 'carrito',
    loadChildren: () =>
      import('./features/petshop/carrito.routes').then(m => m.CARRITO_ROUTES)
  },

  {
    path: 'contacto',
    loadChildren: () =>
      import('./features/petshop/contacto.routes').then(m => m.CONTACTO_ROUTES)
  },

  { path: '**', redirectTo: '' },
];
