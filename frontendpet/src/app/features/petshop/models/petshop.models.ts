export interface CategoriaProducto {
  id: number;
  nombreCategoria: string;
  descripcion?: string;
}

export interface Producto {
  id: number;
  nombre: string;
  descripcion?: string;
  precio: number;
  stock: number;
  imagen?: string;
  categoria?: CategoriaProducto;
}

export interface Contacto {
  nombre: string;
  correo: string;
  mensaje: string;
}

export interface AgregarAlCarritoRequest {
  idCliente: number;
  idProducto: number;
  cantidad: number;
}

export interface ItemCarritoDTO {
  nombre: string;
  cantidad: number;
  precioUnitario: number;
}

export interface CarritoResponseDTO {
  idCarrito: number;
  productos: ItemCarritoDTO[];
  total: number;
}

export interface ItemCarritoLocal {
  producto: Producto;
  cantidad: number;
}

export interface ItemCarritoAgrupado {
  producto: Producto;
  cantidad: number;
  subtotal: number;
}

export interface ClienteCompra {
  rut: string;
  nombre: string;
  apellido: string;
  correo: string;
  telefono: string;
  direccion: string;
}

export interface ProductoCompra {
  idProducto: number;
  cantidad: number;
}

export interface CrearCompraRequest {
  cliente: ClienteCompra;
  productos: ProductoCompra[];
  tipoRetiro: string;
  observaciones?: string;
}

export interface CompraResponseDTO {
  idCompra: number;
  fechaCompra: string;
  total: number;
  estado: string;
  mensaje: string;
}