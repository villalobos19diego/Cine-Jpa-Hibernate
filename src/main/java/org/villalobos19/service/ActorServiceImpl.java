package org.villalobos19.service;

import jakarta.persistence.EntityManager;
import org.villalobos19.entity.Actor;
import org.villalobos19.exception.DatosInvalidosException;
import org.villalobos19.repository.ActorRepository;
import org.villalobos19.repository.BusquedaActorRepository;
import org.villalobos19.repository.CrudRepository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class ActorServiceImpl implements ActorService{

    private final EntityManager manager;
    private CrudRepository<Actor> repository;
    private BusquedaActorRepository busquedaActorRepository;
    public ActorServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository= new ActorRepository(manager);
        this.busquedaActorRepository= new ActorRepository(manager);
    }

    @Override
    public List<Actor> listar() {
        System.out.println("=====lISTAR ACTOR====");
        return repository.listar();
    }

    @Override
    public Optional<Actor> porId(Integer id) {
        System.out.println("=====BUSQUEDA DE ACTOR POR ID====");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Actor actor) {
        try {
            System.out.println("=====GUARDAR ACTOR====");
            manager.getTransaction().begin();
            repository.guardar(actor);
            manager.getTransaction().commit();
//            System.out.println("=====ACTOR GUARDADO====");
        }catch (Exception e){
//            System.out.println("=====ACTOR NO GUARDADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
            JOptionPane.showConfirmDialog(null,"Error en la entrada de datos","CLOSED_OPTION", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            throw new DatosInvalidosException("Datos introducidos son invalidos");

        }
    }

    @Override
    public Actor editar(Integer id) {
        try {
            System.out.println("=====EDITAR EMPLEADO====");
            manager.getTransaction().begin();
            repository.editar(id);
            manager.getTransaction().commit();
//            System.out.println("=====EMPLEADO EDITADO====");
        }catch (Exception e){
//            System.out.println("=====EMPLEADO NO EDITADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        try {
            System.out.println("=====ELIMINAR ACTOR====");
            manager.getTransaction().begin();
            repository.eliminar(id);
            manager.getTransaction().commit();
//            System.out.println("=====ACTOR ELIMINADO====");
        }catch (Exception e){
//            System.out.println("=====ACTOR NO ELIMINADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Actor> actorListDeadPorNacion(String nacion) {
        System.out.println("=====LISTAR ACTORES FALLECIDOS POR NACION: "+nacion.toUpperCase()+" ====");
        return busquedaActorRepository.actorListDeadPorNacion(nacion) ;
    }
}
