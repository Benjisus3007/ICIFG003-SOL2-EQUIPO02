-- Datos iniciales para la tabla CategoriaProducto
INSERT INTO categoria_producto (id, nombre_categoria, descripcion) 
VALUES (1, 'Alimentos', 'Comida balanceada, pellets y snacks para mascotas')
ON DUPLICATE KEY UPDATE nombre_categoria=nombre_categoria;

INSERT INTO categoria_producto (id, nombre_categoria, descripcion) 
VALUES (2, 'Juguetes', 'Artículos interactivos, pelotas y mordedores')
ON DUPLICATE KEY UPDATE nombre_categoria=nombre_categoria;

INSERT INTO categoria_producto (id, nombre_categoria, descripcion) 
VALUES (3, 'Accesorios', 'Collares, arneses, transportadoras y camas')
ON DUPLICATE KEY UPDATE nombre_categoria=nombre_categoria;

-- Datos iniciales para la tabla Cliente
INSERT INTO cliente (id, nombre, correo, telefono)
VALUES (1, 'Juan Pérez', 'juan.perez@example.com', '+56912345678')
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO cliente (id, nombre, correo, telefono)
VALUES (2, 'María Flores', 'maria.flores@example.com', '+56987654321')
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO cliente (id, nombre, correo, telefono)
VALUES (3, 'Carlos Rojas', 'carlos.rojas@example.com', '+56933445566')
ON DUPLICATE KEY UPDATE nombre=nombre;

-- Datos iniciales para la tabla Producto
INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (1, 'Alimento Premium Perro', 'Alimento seco balanceado para perro adulto, bolsa 15kg', 18990, 20, 'https://images.unsplash.com/photo-1589924691995-400dc9ecc119?w=400', 1)
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (2, 'Snack Dental Perro', 'Snacks para limpieza dental canina, pack x20', 4990, 50, 'https://images.unsplash.com/photo-1601758124510-52d02ddb7cbd?w=400', 1)
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (3, 'Alimento Premium Gato', 'Alimento seco para gatos adultos, bolsa 3kg', 9990, 30, 'https://images.unsplash.com/photo-1548681528-6a5c45b66b42?w=400', 1)
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (4, 'Pelota Interactiva', 'Pelota resistente con sonido para perros y gatos', 6990, 35, 'https://images.unsplash.com/photo-1576201836106-db1758fd1c97?w=400', 2)
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (5, 'Mordedor de Cuerda', 'Juguete de cuerda resistente para masticar', 4490, 40, 'https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=400', 2)
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (6, 'Rascador Torre Gato', 'Rascador de sisal con plataformas, 80cm', 19990, 15, 'https://images.unsplash.com/photo-1615789591457-74a63395c990?w=400', 2)
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (7, 'Correa Retractil 5m', 'Correa retractil resistente hasta 25kg', 12990, 25, 'https://images.unsplash.com/photo-1601758003122-53c40e686a19?w=400', 3)
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (8, 'Cama Ortopedica', 'Cama acolchada con soporte ortopedico talla M', 24990, 10, 'https://images.unsplash.com/photo-1541781774459-bb2af2f05b55?w=400', 3)
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (9, 'Comedero Doble Acero', 'Comedero doble acero inoxidable antideslizante', 6990, 35, 'https://images.unsplash.com/photo-1512568400610-62da28bc8a13?w=400', 3)
ON DUPLICATE KEY UPDATE nombre=nombre;

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen, id_categoria)
VALUES (10, 'Transportadora Rigida M', 'Transportadora plastica con ventilacion talla M', 29990, 8, 'https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?w=400', 3)
ON DUPLICATE KEY UPDATE nombre=nombre;