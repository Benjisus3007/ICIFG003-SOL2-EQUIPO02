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
