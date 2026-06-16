-- Datos iniciales para la tabla CategoriaProducto (Corregido a nombre_categoria)
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