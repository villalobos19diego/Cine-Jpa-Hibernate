package org.villalobos19.service;

import jakarta.persistence.EntityManager;
import org.villalobos19.entity.GanaPremio;
import org.villalobos19.repository.CrudRepository;
import org.villalobos19.repository.GanaPremioRepository;

import java.util.List;
import java.util.Optional;

public class GanaPremioServiceImpl implements GanaPremioService{
    private final EntityManager manager;
    private CrudRepository<GanaPremio> repository;

    public GanaPremioServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository= new GanaPremioRepository(manager);
    }

    @Override
    public List<GanaPremio> listar() {
        System.out.println("=====lISTAR GANAPREMIO====");
        return repository.listar();
    }

    @Override
    public Optional<GanaPremio> porId(Integer id) {
        System.out.println("=====BUSQUEDA DE GANAPREMIO POR ID====");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(GanaPremio premio) {
        try {
            System.out.println("=====GUARDAR GANA PREMIO====");
            manager.getTransaction().begin();
            repository.guardar(premio);
            manager.getTransaction().commit();
//            System.out.println("=====GANAPREMIO GUARDADO====");
        }catch (Exception e){
//            System.out.println("=====GANAPREMIO NO GUARDADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Integer id) {
        try {
            System.out.println("=====EDITAR GANAPREMIO====");
            manager.getTransaction().begin();
            repository.editar(id);
            manager.getTransaction().commit();
//            System.out.println("=====GANAPREMIO EDITADO====");
        }catch (Exception e){
//            System.out.println("=====GANAPREMIO NO EDITADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            System.out.println("=====ELIMINAR GANAPREMIO====");
            manager.getTransaction().begin();
            repository.eliminar(id);
            manager.getTransaction().commit();
//            System.out.println("=====GANAPREMIO ELIMINADO====");
        }catch (Exception e){
//            System.out.println("=====GANAPREMIO NO ELIMINADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
