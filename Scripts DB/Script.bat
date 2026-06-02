@echo off
set PGPASSWORD=1234

echo Cerrando conexiones activas a petshopdb...
psql -U postgres -h localhost -p 5432 -d postgres -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname='petshopdb';"

echo Eliminando base de datos petshopdb si existe...
psql -U postgres -h localhost -p 5432 -d postgres -c "DROP DATABASE IF EXISTS petshopdb;"

echo Creando base de datos petshopdb...
psql -U postgres -h localhost -p 5432 -d postgres -c "CREATE DATABASE petshopdb;"

echo Ejecutando script SQL...
psql -U postgres -h localhost -p 5432 -d petshopdb -f "setup.sql"

echo.
echo Proceso finalizado exitosamente.
pause