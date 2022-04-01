# Bases_datos

Proyecto final de bases de datos

## Minuta de Planeacion de Proyecto Entregable 1:

- El Proyecto definido sera el tema de Gimnasio, el cual sera una aplicacion que se conectara a una base de datos ORACLE para la extraccion de los datos.
- La aplicacion se desarrollara en lenguaje JAVA utilizando el framework de SpringBoot
- La base de datos contara con un total aproximado de 8 tablas. El total de tablas puede variar a través de la longitud del Proyecto y dependiendo de las características adicionales que se deseen agregar.
- El Proyecto contara con un repositorio GitHub en donde los miembros del grupo agregaremos nuestros aportes según sea necesario.
- El repositorio GitHub creado es identificado con el [Link](https://github.com/S3B4SZ17/Bases_datos)

## Pasos para crear usuario en la base de datos

`CREATE USER sebas IDENTIFIED BY SebasZumbado1234;`

Despues para que el usuario se pueda conectar hay que asignarle ciertos permisos:

- `GRANT CREATE SESSION TO sebas;`
- `GRANT CREATE TABLE TO sebas;`
- Para obetner el default tablespace: `SELECT default_tablespace FROM dba_users WHERE username = 'sebas';`
- `ALTER USER sebas QUOTA UNLIMITED ON USERS;`
- Para tener permisos de _crear vies_, _crear procedures_, _crear sequencias_: `GRANT create view, create procedure, create sequence TO sebas;`
