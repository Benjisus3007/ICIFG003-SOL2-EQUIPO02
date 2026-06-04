import { Component, OnInit, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CategoriaProducto, Producto } from '../../models/petshop.models';
import { CarritoService } from '../../services/carrito.service';
import { CategoriaService } from '../../services/categoria.service';
import { ProductoService } from '../../services/producto.service';
import { MenuComponent } from '../../components/menu/menu.component';
import { Mensaje } from '../../components/mensaje/mensaje.component';
import { ProductoCard } from '../../components/producto-card/producto-card.component';

@Component({
  selector: 'app-catalogo',
  imports: [FormsModule, MenuComponent, Mensaje, ProductoCard],
  templateUrl: './catalogo.component.html',
  styleUrl: './catalogo.component.css',
})
export class CatalogoComponent implements OnInit {
  private readonly productoService = inject(ProductoService);
  private readonly categoriaService = inject(CategoriaService);
  readonly carritoService = inject(CarritoService);

  private readonly idClienteDemo = 1;

  productos = signal<Producto[]>([]);
  categorias = signal<CategoriaProducto[]>([]);
  categoriaSeleccionada = signal<number | 'todos'>('todos');
  cargando = signal(true);
  mensaje = signal('');
  tipoMensaje = signal<'exito' | 'error' | 'info'>('info');

  productosFiltrados = computed(() => {
    const categoria = this.categoriaSeleccionada();
    if (categoria === 'todos') {
      return this.productos();
    }

    return this.productos().filter((producto) => producto.categoria?.id === Number(categoria));
  });

  ngOnInit(): void {
    this.cargarCategorias();
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.cargando.set(true);
    this.productoService.listarConStock().subscribe({
      next: (productos) => {
        this.productos.set(productos);
        this.cargando.set(false);
      },
      error: () => {
        this.cargando.set(false);
        this.mostrarMensaje('No se pudieron cargar los productos. Revisa que el backend esté funcionando.', 'error');
      },
    });
  }

  cargarCategorias(): void {
    this.categoriaService.listarTodas().subscribe({
      next: (categorias) => this.categorias.set(categorias),
      error: () => this.mostrarMensaje('No se pudieron cargar las categorías.', 'error'),
    });
  }

  filtrarProductos(valor: string): void {
    this.categoriaSeleccionada.set(valor === 'todos' ? 'todos' : Number(valor));
  }

  agregarAlCarrito(producto: Producto): void {
    this.carritoService.agregarLocal(producto);

    this.carritoService.agregarEnBackend({
      idCliente: this.idClienteDemo,
      idProducto: producto.id,
      cantidad: 1,
    }).subscribe({
      next: () => {
        this.mostrarMensaje(
          `${producto.nombre} fue agregado al carrito.`,
          'exito'
        );
      },
      error: () => {
        this.mostrarMensaje(
          `${producto.nombre} fue agregado localmente. Para guardar en BD debe existir el cliente con ID 1.`,
          'info'
        );
      }
    });
}
  private mostrarMensaje(texto: string, tipo: 'exito' | 'error' | 'info'): void {
    this.mensaje.set(texto);
    this.tipoMensaje.set(tipo);
    window.setTimeout(() => this.mensaje.set(''), 4500);
  }
}
