# Cine Jpa-Hibernate

Este proyecto consiste en generar de manera automatica las tablas de una base de datos, relacionarlas y hacer consultas en ellas por medio de las tecnologias de hibernate y Jpa usando como gestor de base de datos Mysql.


> Los registros deben de ser introducidos antes de ejecutar el programa, de manera manual  y atraves del archivo "Import.sql"que esta contenida en:

 ```mermaid
graph LR;
A(src) --> B(main) --> C(resources) --> d(Import.sql)

```
>de las carpetas del  proyecto.

> hibernate.hbm2ddl.auto debe estar en "update".

****

**El proyecto consiste en**

- Crear Tablas de base de datos las cuales son:  directores , actores, peliculas, premios; dos tablas intermedias cuales son: ganapremio, participa.
- Inserta datos a una tabla
- Modifica los registros de las tablas
- Elimina registros
- Listar registros

**Hace consultas tales como:**
- La empresa distribuidora de una pelicula.
- Actores nacidos en un pais y que ya fallecieron.
- Cual ha sido la recaudacion total de un pais.
- Mostrar nombres y lugar de nacimiento de los actores que participaron en la pelicula "solas".
- Mostrar listado de cuantas peliculas hay de cada nacionalidad.
-  Listado de las peliculas españolas pero concatenando el año en el titulo.
-  El actor con mas participaciones.
- Director que ha dirigido dos peliculas
- Que directores no han dirigido ninguna pelicula 
