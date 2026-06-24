# 🐾 PetShop Online — ICIFG003-SOL2-EQUIPO02

---

## 👥 Equipo

| Integrante | Rol |
|---|---|
| Benjamín Aedo | Project Manager + DevOps |
| Sebastián Villa | Backend Lead |
| Felipe Caro | Frontend Lead |

---

## 🛠️ Tecnologías utilizadas

| Capa | Tecnología |
|---|---|
| Backend | Java 17 + Spring Boot 4 |
| Base de datos | MySQL 8.0 |
| Frontend | Angular 21 |
| ORM | Hibernate / Spring Data JPA |
| Contenedores | Docker + Docker Compose |

---

## 📋 Requisitos previos

Para ejecutar con Docker (recomendado):
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

Para ejecutar sin Docker:
- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Node.js v20+](https://nodejs.org/)
- [Angular CLI v21](https://angular.io/cli) → `npm install -g @angular/cli`
- [MySQL 8.0+](https://dev.mysql.com/downloads/)

---

## ⚙️ Configuración y ejecución

### ▶️ Opción A — Con Docker (recomendado)

#### 1. Clonar el repositorio desde la rama QA

```bash
git clone -b qa https://github.com/Benjisus3007/ICIFG003-SOL2-EQUIPO02.git
cd ICIFG003-SOL2-EQUIPO02
```

#### 2. Iniciar todos los servicios

```bash
docker compose up --build
```

Esto levanta automáticamente:
- MySQL 8.0 en el puerto `3306`
- Backend Spring Boot en `http://localhost:8080`
- Frontend Angular en `http://localhost:4200`

> ⚠️ La primera vez puede tardar varios minutos mientras descarga las imágenes de Docker.

> ⚠️ Las imágenes de los productos se cargan desde URLs públicas de Unsplash. Se requiere **conexión a internet** para visualizarlas.

#### 3. Detener los servicios

```bash
docker compose down
```

---

### ▶️ Opción B — Sin Docker

#### 1. Clonar el repositorio desde la rama QA

```bash
git clone -b qa https://github.com/Benjisus3007/ICIFG003-SOL2-EQUIPO02.git
cd ICIFG003-SOL2-EQUIPO02
```

#### 2. Configurar MySQL

Asegúrate de tener MySQL corriendo con:
- Usuario: `root`
- Contraseña: vacía
- Puerto: `3306`

La base de datos `petshopdb` y sus tablas se crean automáticamente al iniciar el backend mediante Hibernate.

#### 3. Iniciar el backend

```bash
cd backendpet
./mvnw spring-boot:run
```

El backend quedará disponible en `http://localhost:8080` y creará las tablas automáticamente.

#### 4. Iniciar el frontend

En otra terminal:

```bash
cd frontendpet
npm install
ng serve
```

El frontend quedará disponible en `http://localhost:4200`.

---

## 🌐 Endpoints principales del backend

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/productos` | Lista todos los productos |
| GET | `/api/productos/categoria/{id}` | Filtra productos por categoría |
| GET | `/api/categorias` | Lista todas las categorías |
| POST | `/api/carritos/agregar` | Agrega un producto al carrito |
| POST | `/api/contacto` | Envía un mensaje de contacto |

---

## 📁 Estructura del repositorio

```
ICIFG003-SOL2-EQUIPO02/
├── backendpet/                        # Proyecto Spring Boot
│   └── src/main/
│       ├── java/com/backendpet/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── repository/
│       │   └── service/
│       └── resources/
│           ├── application.properties
│           └── data.sql               # Datos de prueba (DML)
├── frontendpet/                       # Proyecto Angular
│   └── src/app/features/petshop/
│       ├── components/
│       ├── models/
│       ├── pages/
│       └── services/
├── docker-compose.yml                 # Orquestación de servicios
└── README.md
```

---

## ✅ Funcionalidades implementadas

- **RF01** — Página principal con header, navegación, productos destacados, sidebar de promociones y footer
- **RF02** — Catálogo de productos cargado dinámicamente desde el backend
- **RF03** — Filtrado de productos por categoría
- **RF04** — Carrito de compras con contador, total acumulado y persistencia en base de datos
- **RF05** — Formulario de contacto con validaciones individuales por campo y persistencia en base de datos

---

## 🐳 Arquitectura Docker

```
┌─────────────────────────────────────────┐
│           docker-compose.yml            │
│                                         │
│  ┌──────────┐  ┌──────────┐  ┌───────┐ │
│  │ mysql_db │  │ backend  │  │front  │ │
│  │ :3306    │◄─│ :8080    │◄─│end    │ │
│  └──────────┘  └──────────┘  │:4200  │ │
│                               └───────┘ │
└─────────────────────────────────────────┘
```