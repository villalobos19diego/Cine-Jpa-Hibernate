package org.villalobos19.service;

import jakarta.persistence.EntityManager;
import org.villalobos19.entity.Genero;
import org.villalobos19.repository.CrudRepository;
import org.villalobos19.repository.GeneroRepository;

import java.util.List;
import java.util.Optional;

public class GeneroServiceImpl implements GeneroService{
    private final EntityManager manager;
    private CrudRepository<Genero> repository;
    public GeneroServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository= new GeneroRepository(manager);
    }

    @Override
    public List<Genero> listar() {
        System.out.println("=====lISTAR GENERO====");
        return repository.listar();
    }

    @Override
    public Optional<Genero> porId(Integer id) {
        System.out.println("=====BUSQUEDA DE GENERO POR ID====");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Genero genero) {
        try {
            System.out.println("=====GUARDAR GENERO PELICULA====");
            manager.getTransaction().begin();
            repository.guardar(genero);
            manager.getTransaction().commit();
//            System.out.println("=====GENERO GUARDADO====");
        }catch (Exception e){
//            System.out.println("=====GENERO NO GUARDADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Integer id) {
        try {
            System.out.println("=====EDITAR GENERO====");
            manager.getTransaction().begin();
            repository.editar(id);
            manager.getTransaction().commit();
//            System.out.println("=====GENERO EDITADO====");
        }catch (Exception e){
//            System.out.println("=====GENERO NO EDITADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            System.out.println("=====ELIMINAR GENERO====");
            manager.getTransaction().begin();
            repository.eliminar(id);
            manager.getTransaction().commit();
//            System.out.println("=====GENERO ELIMINADO====");
        }catch (Exception e){
//            System.out.println("=====GENERO NO ELIMINADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
