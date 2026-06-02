-- Tablas
CREATE TABLE IF NOT EXISTS cliente (
    id SERIAL PRIMARY KEY,
    rut VARCHAR(20),
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    correo VARCHAR(150),
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    fecha_registro DATE
);

CREATE TABLE IF NOT EXISTS categoria_producto (
    id SERIAL PRIMARY KEY,
    nombre_categoria VARCHAR(100),
    descripcion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150),
    descripcion TEXT,
    precio NUMERIC(10,2),
    stock INTEGER,
    imagen VARCHAR(255),
    id_categoria INTEGER REFERENCES categoria_producto(id)
);

CREATE TABLE IF NOT EXISTS mascota (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    especie VARCHAR(50),
    raza VARCHAR(100),
    sexo VARCHAR(10),
    fecha_nacimiento DATE,
    peso NUMERIC(5,2),
    id_cliente INTEGER REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS carrito (
    id SERIAL PRIMARY KEY,
    fecha_creacion TIMESTAMP,
    id_cliente INTEGER REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS detalle_carrito (
    id SERIAL PRIMARY KEY,
    precio_unitario NUMERIC(10,2),
    cantidad INTEGER,
    id_carrito INTEGER REFERENCES carrito(id),
    id_producto INTEGER REFERENCES producto(id)
);

CREATE TABLE IF NOT EXISTS contacto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_envio TIMESTAMP
);

-- Datos
INSERT INTO cliente (rut, nombre, apellido, correo, telefono, direccion, fecha_registro) VALUES
('12345678-9', 'Juan', 'Perez', 'juan@mail.com', '987654321', 'Concepcion', CURRENT_DATE),
('98765432-1', 'Maria', 'Gonzalez', 'maria@mail.com', '912345678', 'Chiguayante', CURRENT_DATE);

INSERT INTO categoria_producto (nombre_categoria, descripcion) VALUES
('Perros', 'Productos especiales para perros'),
('Gatos', 'Productos especiales para gatos'),
('Accesorios', 'Accesorios generales para mascotas'),
('Higiene', 'Productos de higiene y limpieza para mascotas');

INSERT INTO producto (nombre, descripcion, precio, stock, imagen, id_categoria) VALUES
('Alimento Premium Perro', 'Alimento seco balanceado para perro adulto, bolsa 15kg', 18990, 20, 'alimento_perro.jpg', 1),
('Snack Dental Perro', 'Snacks para limpieza dental canina, pack x20', 4990, 50, 'snack_dental.jpg', 1),
('Cama Ortopedica Perro', 'Cama acolchada con soporte ortopedico talla M', 24990, 10, 'cama_perro.jpg', 1),
('Alimento Premium Gato', 'Alimento seco para gatos adultos, bolsa 3kg', 9990, 30, 'alimento_gato.jpg', 2),
('Arena Sanitaria Aglomerante', 'Arena sanitaria para gatos bajo olor, bolsa 10kg', 7990, 40, 'arena.jpg', 2),
('Rascador Torre Gato', 'Rascador de sisal con plataformas, 80cm', 19990, 15, 'rascador.jpg', 2),
('Correa Retractil 5m', 'Correa retractil resistente hasta 25kg', 12990, 25, 'correa.jpg', 3),
('Comedero Doble Acero', 'Comedero doble acero inoxidable antideslizante', 6990, 35, 'comedero.jpg', 3),
('Transportadora Rigida M', 'Transportadora plastica con ventilacion talla M', 29990, 8, 'transportadora.jpg', 3),
('Shampoo Mascotas Neutro', 'Shampoo hipoalergenico para perros y gatos 500ml', 5490, 45, 'shampoo.jpg', 4);

INSERT INTO mascota (nombre, especie, raza, sexo, fecha_nacimiento, peso, id_cliente) VALUES
('Firulais', 'Perro', 'Labrador', 'Macho', '2021-03-15', 28.5, 1),
('Michi', 'Gato', 'Siames', 'Hembra', '2022-07-10', 4.2, 2);

INSERT INTO carrito (fecha_creacion, id_cliente) VALUES
(CURRENT_TIMESTAMP, 1),
(CURRENT_TIMESTAMP, 2);

INSERT INTO detalle_carrito (precio_unitario, cantidad, id_carrito, id_producto) VALUES
(18990, 1, 1, 1),
(12990, 1, 1, 7),
(9990, 1, 2, 4),
(7990, 2, 2, 5);

INSERT INTO contacto (nombre, correo, mensaje, fecha_envio) VALUES
('Ana Torres', 'ana@mail.com', 'Quisiera saber si tienen delivery disponible para mi ciudad.', CURRENT_TIMESTAMP),
('Luis Soto', 'luis@mail.com', 'Consulta sobre disponibilidad de productos para gatos mayores.', CURRENT_TIMESTAMP);