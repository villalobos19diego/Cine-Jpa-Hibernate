package org.villalobos19;

import jakarta.persistence.EntityManager;
import org.villalobos19.entity.*;
import org.villalobos19.repository.Pais;
import org.villalobos19.repository.TipoBusqueda;
import org.villalobos19.repository.TipoMoneda;
import org.villalobos19.service.*;
import org.villalobos19.util.JpaUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();

        // Crear instancias de los servicios y repositorios necesarios
        ActorService actorService = new ActorServiceImpl(manager);
        PeliculaService peliculaService = new PeliculaServiceImpl(manager);
        ParticipaService participaService = new ParticipaServiceImpl(manager);
        DirectorService directorService = new DirectorServiceImpl(manager);

        // Guardar un nuevo actor en la base de datos
        Actor actor = actor();
        actorService.guardar(actor);

        // Listar todos los actores
        List<Actor> actorList = actorService.listar();
        actorList.forEach(System.out::println);

        // Listar todas las películas
        List<Pelicula> peliculaList = peliculaService.listar();
        peliculaList.forEach(System.out::println);

        // Obtener una película por su id
        Integer id = 6;
        Pelicula pelicula = peliculaService.porId(id);

        // Crear una instancia de Participa y guardarla en la base de datos
        Participa participa = new Participa();
        participa.setActor(actor);
        participa.setPelicula(pelicula);
        participaService.guardar(participa);

        // Listar todas las participaciones por id de película
        List<Participa> participaList = participaService.listarPorId(6, TipoBusqueda.POR_PELICULA);
        participaList.forEach(System.out::println);

        // Cambiar la moneda de las películas por país
        peliculaService.cambiarMonedaPorPais(Pais.EEUU, TipoMoneda.EURO);
        peliculaList = peliculaService.listar();
        peliculaList.forEach(System.out::println);

        // Obtener la distribuidora de la película "Casablanca"
        System.out.println("Distribuidora de Casablanca: " + peliculaService.distribuidora("Casablanca"));

        // Obtener actores fallecidos por nacionalidad
        actorList = actorService.actorListDeadPorNacion("Suecia");
        actorList.forEach(actor1 -> System.out.println("Nombre: " + actor1.getNombre()
                + " Nacionalidad: " + actor1.getNacionalidad()
                + " Fecha de muerte: " + actor1.getFechaMuerte())
        );

        // Obtener la recaudación de películas por nacionalidad
        List<Object[]> resultados = peliculaService.recaudacionPeliculasNacion("España");
        resultados.forEach(objects -> {
            String nacionalidad = (String) objects[0];
            double recaudacion = (double) objects[1];
            System.out.println("Nacionalidad: " + nacionalidad + ", Recaudacion:$ " + BigDecimal.valueOf(recaudacion));
        });
        // Obtener los actores de películas por iud
        id=10;
        participaList=participaService.listarPorId(10,TipoBusqueda.POR_PELICULA);
        participaList.forEach(System.out::println);

        //Busqueda de participaciones de naciones
       resultados=peliculaService.peliculaPorNacion();
       resultados.forEach(objects -> {
           String nacionalidad = (String) objects[0];
           Long participaciones = (Long) objects[1];
           System.out.println("Naciones: "+nacionalidad+" participaciones: "+participaciones);
       });
        //Busqueda de participaciones por nacion
        resultados=peliculaService.peliculaPorNacion("España");
        resultados.forEach(objects -> {
            String nacionalidad = (String) objects[0];
            Long participaciones = (Long) objects[1];
            System.out.println("Naciones: "+nacionalidad+" participaciones: "+participaciones);
        });

        // Obtener todas las películas concatenando el título con el año y la nacionalidad
        resultados = peliculaService.peliculaConcatenandoAnyo("España");
        resultados.forEach(objects -> {
            String titulo = (String) objects[0];
            String anyo = (String) objects[1];
            String tituloConcat = (String) objects[2];
            String nacionalidad = (String) objects[3];
            System.out.println("Titulo: " + titulo + " Anyo: " + anyo + " Titulo Concatenado: " + tituloConcat + " Nacionalidad: " + nacionalidad);
        });

        // Obtener el actor con la mayor cantidad de participaciones
        resultados = participaService.actorMayorParticipacion();
        resultados.forEach(objects -> {
            Actor actor1 = (Actor) objects[0];
            Long cantidad = (Long) objects[1];
            System.out.println("Actor: " + actor1.getNombre() + " Nacionalidad: " + actor1.getNacionalidad() + " Cantidad: " + cantidad);
        });

        // Obtener los directores con su cantidad de participaciones
        Long dir = 2L;
        resultados = directorService.directorParticipaciones(dir);
        resultados.forEach(objects -> {
            Director director = (Director) objects[0];
            Long cantidad = (Long) objects[1];
            System.out.println("Id: " + director.getId() + " Director: " + director.getNombre() + " Nacionalidad: " + director.getNacionalidad() + " Cantidad: " + cantidad);
        });

        // Obtener los directores sin participaciones
        List<Director> directores = directorService.directorSinParticipaciones();
        directores.forEach(director -> System.out.println("Nombre: " + director.getNombre() + " Nacionalidad: " + director.getNacionalidad()));


        // Cerrar el EntityManager
        manager.close();
    }

    public static Actor actor() {
        Actor actor = new Actor();
        actor.setNombre("Diego");
        actor.setNacionalidad("Salvadorenia");
        actor.setFechaNacimiento(LocalDateTime.of(LocalDate.of(2004, 3, 13), LocalTime.MIDNIGHT));
        actor.setLugarNacimiento("San Salvador, El Salvador");
        return actor;
    }
}