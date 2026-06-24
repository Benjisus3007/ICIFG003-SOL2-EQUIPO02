# 🐾 PetShop Online — ICIFG003-SOL2-EQUIPO02

## 👥 Equipo

* Integrante 1 — Felipe Caro
* Integrante 2 — Sebastián Villa
* Integrante 3 — Benjamín Aedo

---

## 🛠️ Tecnologías utilizadas

| Capa          | Tecnología                  |
| ------------- | --------------------------- |
| Backend       | Java 17 + Spring Boot 4     |
| Base de datos | MySQL 8.0                   |
| Frontend      | Angular 21                  |
| ORM           | Hibernate / Spring Data JPA |
| Contenedores  | Docker + Docker Compose     |

---

## 📋 Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

* Docker Desktop
* Docker Compose

Al usar Docker Compose no es necesario instalar manualmente Java, Node.js, Angular CLI ni MySQL, ya que estos servicios se ejecutan dentro de contenedores.

---

## ⚙️ Configuración y ejecución con Docker Compose

### 1. Clonar el repositorio desde la branch `qa`

```bash
git clone -b qa https://github.com/Benjisus3007/ICIFG003-SOL2-EQUIPO02.git
cd ICIFG003-SOL2-EQUIPO02
```

El proyecto debe ejecutarse desde la branch `qa`, ya que allí se encuentra la versión preparada para levantar los servicios mediante Docker Compose.

---

### 2. Iniciar el proyecto

Desde la carpeta raíz del proyecto, ejecutar:

```bash
docker compose up --build
```

Este comando construye e inicia los contenedores necesarios para el funcionamiento del sistema:

* Base de datos MySQL
* Backend Spring Boot
* Frontend Angular

---

### 3. Acceder a la aplicación

Una vez iniciados los contenedores, el proyecto estará disponible en:

| Servicio            | URL                   |
| ------------------- | --------------------- |
| Frontend            | http://localhost:4200 |
| Backend             | http://localhost:8080 |
| Base de datos MySQL | localhost:3306        |

---

## 🗄️ Base de datos

La base de datos utilizada por el sistema es:

```text
petshopdb
```

La base de datos se levanta automáticamente mediante Docker Compose.

Configuración principal:

| Dato          | Valor     |
| ------------- | --------- |
| Motor         | MySQL 8.0 |
| Base de datos | petshopdb |
| Puerto        | 3306      |
| Usuario       | root      |
| Contraseña    | Vacía     |

El backend se conecta a MySQL usando la URL configurada en `application.properties`.

Dentro de Docker, el backend no debe conectarse a `localhost`, sino al nombre del servicio definido para MySQL en `docker-compose.yml`.

---

## 📦 Carga de datos iniciales

Los datos iniciales del sistema se cargan desde el archivo:

```text
backendpet/src/main/resources/data.sql
```

El backend tiene configurado Spring Data JPA/Hibernate para crear o actualizar las tablas y luego cargar los datos iniciales.

El archivo `Script.bat` de la carpeta `Scripts DB` corresponde a una ejecución manual antigua y no es necesario usarlo cuando se ejecuta el proyecto con Docker Compose.

---

## 🛑 Detener el proyecto

Para detener los contenedores, presionar:

```bash
Ctrl + C
```

Luego ejecutar:

```bash
docker compose down
```

---

## 🔄 Reiniciar completamente la base de datos

Si quieres borrar la base de datos y crearla nuevamente desde cero, ejecuta:

```bash
docker compose down -v
docker compose up --build
```

El comando `docker compose down -v` elimina también el volumen de MySQL, por lo que se perderán los datos guardados anteriormente.

---

## 📌 Comandos útiles

Ver contenedores activos:

```bash
docker ps
```

Ver logs de todos los servicios:

```bash
docker compose logs
```

Ver logs del backend:

```bash
docker compose logs backend
```

Ver logs del frontend:

```bash
docker compose logs frontend
```

Ver logs de MySQL:

```bash
docker compose logs mysql_db
```

Detener y eliminar contenedores:

```bash
docker compose down
```

Detener y eliminar contenedores junto con la base de datos:

```bash
docker compose down -v
```

Reconstruir el proyecto:

```bash
docker compose up --build
```

Reconstruir sin usar caché:

```bash
docker compose build --no-cache
docker compose up
```

---

## 🌐 Endpoints principales del backend

| Método | Ruta                            | Descripción                    |
| ------ | ------------------------------- | ------------------------------ |
| GET    | `/api/productos`                | Lista todos los productos      |
| GET    | `/api/productos/categoria/{id}` | Filtra productos por categoría |
| GET    | `/api/categorias`               | Lista todas las categorías     |
| POST   | `/api/carritos/agregar`         | Agrega un producto al carrito  |
| POST   | `/api/contacto`                 | Envía un mensaje de contacto   |

---

## 📁 Estructura del repositorio

```text
ICIFG003-SOL2-EQUIPO02/
├── backendpet/
│   ├── Dockerfile
│   └── src/main/
│       ├── java/com/backendpet/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── repository/
│       │   └── service/
│       └── resources/
│           ├── application.properties
│           └── data.sql
├── frontendpet/
│   ├── Dockerfile
│   └── src/app/features/petshop/
│       ├── components/
│       ├── models/
│       ├── pages/
│       └── services/
├── Scripts DB/
│   └── Script.bat
├── docker-compose.yml
└── README.md
```

---

## ✅ Funcionalidades implementadas

* RF01 — Página principal con header, navegación, productos destacados, sidebar de promociones y footer.
* RF02 — Catálogo de productos cargado dinámicamente desde el backend.
* RF03 — Filtrado de productos por categoría.
* RF04 — Carrito de compras con contador y total acumulado.
* RF05 — Formulario de contacto con validaciones.

---

## ⚠️ Consideraciones importantes

* El frontend requiere que el backend esté corriendo para cargar los productos.
* El backend requiere que MySQL esté activo antes de conectarse correctamente.
* Docker Compose se encarga de levantar los servicios necesarios.
* Las imágenes de los productos se cargan desde URLs públicas de Unsplash, por lo que se requiere conexión a internet para visualizarlas.
* Si se cambia el nombre del servicio de MySQL en `docker-compose.yml`, también debe actualizarse la URL de conexión del backend.

---

## ▶️ Ejecución resumida

```bash
git clone -b qa https://github.com/Benjisus3007/ICIFG003-SOL2-EQUIPO02.git
cd ICIFG003-SOL2-EQUIPO02
docker compose up --build
```

Luego abrir en el navegador:

```text
http://localhost:4200
```
