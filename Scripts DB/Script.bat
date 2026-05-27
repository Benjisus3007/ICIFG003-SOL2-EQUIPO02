@echo off

set PGPASSWORD=1234

echo Cerrando conexiones activas a petshopdb...
"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d postgres -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'petshopdb';"

echo Eliminando base de datos petshopdb si existe...
"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d postgres -c "DROP DATABASE IF EXISTS petshopdb;"

echo Creando base de datos petshopdb...
"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d postgres -c "CREATE DATABASE petshopdb;"

echo Creando tablas e insertando datos...

"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "CREATE TABLE IF NOT EXISTS cliente (id SERIAL PRIMARY KEY, rut VARCHAR(20), nombre VARCHAR(100), apellido VARCHAR(100), correo VARCHAR(150), telefono VARCHAR(20), direccion VARCHAR(255), fecha_registro DATE);"

"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "CREATE TABLE IF NOT EXISTS categoria_producto (id SERIAL PRIMARY KEY, nombre_categoria VARCHAR(100), descripcion VARCHAR(255));"

"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "CREATE TABLE IF NOT EXISTS producto (id SERIAL PRIMARY KEY, nombre VARCHAR(150), descripcion TEXT, precio NUMERIC(10,2), stock INTEGER, imagen VARCHAR(255), id_categoria INTEGER REFERENCES categoria_producto(id));"

"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "CREATE TABLE IF NOT EXISTS mascota (id SERIAL PRIMARY KEY, nombre VARCHAR(100), especie VARCHAR(50), raza VARCHAR(100), sexo VARCHAR(10), fecha_nacimiento DATE, peso NUMERIC(5,2), id_cliente INTEGER REFERENCES cliente(id));"

"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "CREATE TABLE IF NOT EXISTS carrito (id SERIAL PRIMARY KEY, fecha_creacion TIMESTAMP, id_cliente INTEGER REFERENCES cliente(id));"

"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "CREATE TABLE IF NOT EXISTS detalle_carrito (id SERIAL PRIMARY KEY, precio_unitario NUMERIC(10,2), cantidad INTEGER, id_carrito INTEGER REFERENCES carrito(id), id_producto INTEGER REFERENCES producto(id));"

echo Insertando clientes...
"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "INSERT INTO cliente (rut, nombre, apellido, correo, telefono, direccion, fecha_registro) VALUES ('12345678-9', 'Juan', 'Perez', 'juan@mail.com', '987654321', 'Concepcion', CURRENT_DATE), ('98765432-1', 'Maria', 'Gonzalez', 'maria@mail.com', '912345678', 'Chiguayante', CURRENT_DATE);"

echo Insertando categorias...
"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "INSERT INTO categoria_producto (nombre_categoria, descripcion) VALUES ('Alimento', 'Comida para mascotas'), ('Higiene', 'Productos de aseo'), ('Juguetes', 'Juguetes para mascotas');"

echo Insertando productos...
"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "INSERT INTO producto (nombre, descripcion, precio, stock, imagen, id_categoria) VALUES ('Alimento perro 10kg', 'Alimento seco para perro adulto', 24990, 20, 'alimento_perro.jpg', 1), ('Shampoo antipulgas', 'Shampoo para perros y gatos', 7990, 15, 'shampoo.jpg', 2), ('Pelota mordedora', 'Juguete resistente para perro', 4990, 30, 'pelota.jpg', 3);"

echo Insertando mascotas...
"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "INSERT INTO mascota (nombre, especie, raza, sexo, fecha_nacimiento, peso, id_cliente) VALUES ('Firulais', 'Perro', 'Labrador', 'Macho', '2021-03-15', 28.5, 1), ('Michi', 'Gato', 'Siames', 'Hembra', '2022-07-10', 4.2, 2);"

echo Insertando carritos...
"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "INSERT INTO carrito (fecha_creacion, id_cliente) VALUES (CURRENT_TIMESTAMP, 1), (CURRENT_TIMESTAMP, 2);"

echo Insertando detalles de carrito...
"C:\Program Files\PostgreSQL\13\bin\psql.exe" -U postgres -h localhost -p 5432 -d petshopdb -c "INSERT INTO detalle_carrito (precio_unitario, cantidad, id_carrito, id_producto) VALUES (24990, 1, 1, 1), (4990, 2, 1, 3), (7990, 1, 2, 2);"

echo Proceso finalizado.
pause