package org.villalobos19.service;

import jakarta.persistence.EntityManager;
import org.villalobos19.entity.Director;
import org.villalobos19.repository.BusquedaDirectorRepository;
import org.villalobos19.repository.CrudRepository;
import org.villalobos19.repository.DirectorRepository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class DirectorServiceImpl implements DirectorService{
    private final EntityManager manager;
    private CrudRepository<Director> repository;
    private BusquedaDirectorRepository busquedaDirectorRepository;
    public DirectorServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository= new DirectorRepository(manager);
        this.busquedaDirectorRepository= new DirectorRepository(manager);
    }

    @Override
    public List<Director> listar() {
        System.out.println("=====lISTAR DIRECTOR====");
        return repository.listar();
    }

    @Override
    public Optional<Director> porId(Integer id) {
//        System.out.println("=====BUSQUEDA DE DIRECTOR POR ID====");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Director director) {
        try {
            System.out.println("=====GUARDAR DIRECTOR====");
            manager.getTransaction().begin();
            repository.guardar(director);
            manager.getTransaction().commit();
//            System.out.println("=====DIRECTOR GUARDADO====");
        }catch (Exception e){
//            System.out.println("=====DIRECTOR NO GUARDADO====");
            JOptionPane.showConfirmDialog(null,"Error en la entrada de datos","CLOSED_OPTION", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Integer id) {
        try {
            System.out.println("=====EDITAR DIRECTOR====");
            manager.getTransaction().begin();
            repository.editar(id);
            manager.getTransaction().commit();
//            System.out.println("=====DIRECTOR EDITADO====");
        }catch (Exception e){
//            System.out.println("=====DIRECTOR NO EDITADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            System.out.println("=====ELIMINAR DIRECTOR====");
            manager.getTransaction().begin();
            repository.eliminar(id);
            manager.getTransaction().commit();
//            System.out.println("=====DIRECTOR ELIMINADO====");
        }catch (Exception e){
//            System.out.println("=====DIRECTOR NO ELIMINADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Object[]> directorParticipaciones(Long participaciones) {
        System.out.println("======DIRECTORES CON "+participaciones+" PARTICIPACIONES EN PELICULAS====");
        return busquedaDirectorRepository.directorParticipaciones(participaciones);
    }

    @Override
    public List<Director> directorSinParticipaciones() {
        System.out.println("=====DIRECTORES SIN PARTICIPACIONES EN PELICULAS====");
        return busquedaDirectorRepository.directorSinParticipaciones();
    }
}
