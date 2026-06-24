🐾 PetShop Online — ICIFG003-SOL2-EQUIPO02
👥 Equipo
•	Integrante 1 — Felipe Caro
•	Integrante 2 — Sebastián Villa
•	Integrante 3 — Benjamín Aedo
________________________________________
🛠️ Tecnologías utilizadas
Capa	Tecnología
Backend	Java 17 + Spring Boot 4
Base de datos	MySQL 8.0
Frontend	Angular 21
ORM	Hibernate / Spring Data JPA
Contenedores	Docker + Docker Compose
________________________________________
📋 Requisitos previos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
•	Docker Desktop
•	Docker Compose
Al usar Docker Compose no es necesario instalar manualmente Java, Node.js, Angular CLI ni MySQL, ya que estos servicios se ejecutan dentro de contenedores.
________________________________________
⚙️ Configuración y ejecución con Docker Compose
1. Clonar el repositorio desde la branch qa
git clone -b qa https://github.com/Benjisus3007/ICIFG003-SOL2-EQUIPO02.git
cd ICIFG003-SOL2-EQUIPO02
El proyecto debe ejecutarse desde la branch qa, ya que allí se encuentra la versión preparada para levantar los servicios mediante Docker Compose.
________________________________________
2. Iniciar el proyecto
Desde la carpeta raíz del proyecto, ejecutar:
docker compose up --build
Este comando construye e inicia los contenedores necesarios para el funcionamiento del sistema:
•	Base de datos MySQL
•	Backend Spring Boot
•	Frontend Angular
________________________________________
3. Acceder a la aplicación
Una vez iniciados los contenedores, el proyecto estará disponible en:
Servicio	URL
Frontend	http://localhost:4200

Backend	http://localhost:8080

Base de datos MySQL	localhost:3306
________________________________________
🗄️ Base de datos
La base de datos utilizada por el sistema es:
petshopdb
La base de datos se levanta automáticamente mediante el servicio mysql_db definido en el archivo docker-compose.yml.
Configuración principal de la base de datos:
Dato	Valor
Motor	MySQL 8.0
Servicio Docker	mysql_db
Contenedor	petshop_mysql
Base de datos	petshopdb
Puerto	3306
Usuario	root
Contraseña	Vacía
El backend se conecta a MySQL usando la siguiente URL interna de Docker:
spring.datasource.url=jdbc:mysql://mysql_db:3306/petshopdb?serverTimezone=UTC&createDatabaseIfNotExist=true
Dentro de Docker, el backend no se conecta a localhost, sino al nombre del servicio de la base de datos: mysql_db.
________________________________________
📦 Carga de datos iniciales
Los datos iniciales del sistema se cargan desde el archivo:
backendpet/src/main/resources/data.sql
El backend tiene configurado:
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=update
Esto permite que Spring Boot cree o actualice las tablas mediante JPA/Hibernate y luego inserte los datos iniciales definidos en data.sql.
El archivo Script.bat de la carpeta Scripts DB corresponde a una ejecución manual antigua y no es necesario usarlo cuando se ejecuta el proyecto con Docker Compose.
________________________________________
🛑 Detener el proyecto
Para detener los contenedores, presionar:
Ctrl + C
Luego ejecutar:
docker compose down
________________________________________
🔄 Reiniciar completamente la base de datos
Docker mantiene los datos de MySQL en un volumen llamado mysql_data.
Si quieres borrar la base de datos y crearla nuevamente desde cero, ejecuta:
docker compose down -v
docker compose up --build
El comando docker compose down -v elimina también el volumen de MySQL, por lo que se perderán los datos guardados anteriormente.
________________________________________
📌 Comandos útiles
Ver contenedores activos:
docker ps
Ver logs de todos los servicios:
docker compose logs
Ver logs del backend:
docker compose logs backend
Ver logs del frontend:
docker compose logs frontend
Ver logs de MySQL:
docker compose logs mysql_db
Detener y eliminar contenedores:
docker compose down
Detener y eliminar contenedores junto con la base de datos:
docker compose down -v
Reconstruir el proyecto:
docker compose up --build
Reconstruir sin usar caché:
docker compose build --no-cache
docker compose up
________________________________________
🌐 Endpoints principales del backend
Método	Ruta	Descripción
GET	/api/productos	Lista todos los productos
GET	/api/productos/categoria/{id}	Filtra productos por categoría
GET	/api/categorias	Lista todas las categorías
POST	/api/carritos/agregar	Agrega un producto al carrito
POST	/api/contacto	Envía un mensaje de contacto
________________________________________
📁 Estructura del repositorio
ICIFG003-SOL2-EQUIPO02/
├── backendpet/          # Proyecto Spring Boot
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
├── frontendpet/         # Proyecto Angular
│   ├── Dockerfile
│   └── src/app/features/petshop/
│       ├── components/
│       ├── models/
│       ├── pages/
│       └── services/
├── Scripts DB/
│   └── Script.bat       # Script manual antiguo, no necesario con Docker Compose
├── docker-compose.yml   # Orquestación de frontend, backend y MySQL
└── README.md
________________________________________
✅ Funcionalidades implementadas
•	RF01 — Página principal con header, navegación, productos destacados, sidebar de promociones y footer.
•	RF02 — Catálogo de productos cargado dinámicamente desde el backend.
•	RF03 — Filtrado de productos por categoría.
•	RF04 — Carrito de compras con contador y total acumulado.
•	RF05 — Formulario de contacto con validaciones.
________________________________________
⚠️ Consideraciones importantes
•	El frontend requiere que el backend esté corriendo para cargar los productos.
•	El backend requiere que MySQL esté activo antes de conectarse correctamente.
•	Docker Compose se encarga de levantar los servicios y verificar que MySQL esté disponible antes de iniciar el backend.
•	Las imágenes de los productos se cargan desde URLs públicas de Unsplash, por lo que se requiere conexión a internet para visualizarlas.
•	Si se cambia el nombre del servicio mysql_db en docker-compose.yml, también debe actualizarse la URL de conexión en application.properties.
________________________________________
▶️ Ejecución resumida
git clone -b qa https://github.com/Benjisus3007/ICIFG003-SOL2-EQUIPO02.git
cd ICIFG003-SOL2-EQUIPO02
docker compose up --build
Luego abrir en el navegador:
http://localhost:4200

