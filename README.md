# 🐾 PetShop Online — ICIFG003-SOL2-EQUIPO02

---

## 👥 Equipo

- Integrante 1 — Felipe Caro
- Integrante 2 — Sebastián Villa
- Integrante 3 — Benjamín Aedo

---

## 🛠️ Tecnologías utilizadas

| Capa | Tecnología |
|---|---|
| Backend | Java 17 + Spring Boot 4 |
| Base de datos | PostgreSQL 13 |
| Frontend | Angular 21 |
| ORM | Hibernate / Spring Data JPA |

---

## 📋 Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Node.js v20+](https://nodejs.org/)
- [Angular CLI v21](https://angular.io/cli) → `npm install -g @angular/cli`
- [PostgreSQL 13+](https://www.postgresql.org/download/)
- `psql` disponible en el PATH del sistema

---

## ⚙️ Configuración y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/Benjisus3007/ICIFG003-SOL2-EQUIPO02.git
cd ICIFG003-SOL2-EQUIPO02
```

### 2. Crear la base de datos

Navega a la carpeta `Scripts DB` y ejecuta el script:

```bash
cd "Scripts DB"
Script.bat
```

Esto creará automáticamente la base de datos `petshopdb` con todas las tablas y datos de prueba.

> ⚠️ El script asume que PostgreSQL está configurado con usuario `postgres` y contraseña `1234` en el puerto `5432`. Si tu configuración es diferente, edita el archivo `Script.bat` antes de ejecutarlo.

### 3. Iniciar el backend

```bash
cd backendpet
./mvnw spring-boot:run
```

O desde Visual Studio Code con la extensión de Spring Boot: abre el proyecto y presiona **Run**.

El backend quedará disponible en: `http://localhost:8080`

> ⚠️ El backend debe iniciarse **después** de ejecutar el script de base de datos.

### 4. Iniciar el frontend

En otra terminal:

```bash
cd frontendpet
npm install ( Solo si no está instalado )
ng serve
```

El frontend quedará disponible en: `http://localhost:4200`

> ⚠️ El frontend requiere que el backend esté corriendo para cargar los productos.

> ⚠️ Las imágenes de los productos se cargan desde URLs públicas de Unsplash. Se requiere **conexión a internet** para visualizarlas.

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
├── backendpet/          # Proyecto Spring Boot
│   └── src/main/java/com/backendpet/
│       ├── controller/
│       ├── dto/
│       ├── entity/
│       ├── repository/
│       └── service/
├── frontendpet/         # Proyecto Angular
│   └── src/app/features/petshop/
│       ├── components/
│       ├── models/
│       ├── pages/
│       └── services/
└── Scripts DB/
    ├── Script.bat       # Script de creación y carga de BD
    └── setup.sql        # DDL + datos de prueba
```

---

## ✅ Funcionalidades implementadas

- **RF01** — Página principal con header, navegación, productos destacados, sidebar de promociones y footer
- **RF02** — Catálogo de productos cargado dinámicamente desde el backend
- **RF03** — Filtrado de productos por categoría
- **RF04** — Carrito de compras con contador y total acumulado
- **RF05** — Formulario de contacto con validaciones