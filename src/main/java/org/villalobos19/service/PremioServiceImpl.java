package org.villalobos19.service;

import jakarta.persistence.EntityManager;
import org.villalobos19.entity.Premio;
import org.villalobos19.repository.CrudRepository;
import org.villalobos19.repository.PremioRespository;

import java.util.List;
import java.util.Optional;

public class PremioServiceImpl implements PremioService{
    private final EntityManager manager;
    private CrudRepository<Premio> repository;

    public PremioServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository=new PremioRespository(manager);
    }

    @Override
    public List<Premio> listar() {
        System.out.println("=====lISTAR PREMIO====");
        return repository.listar();
    }

    @Override
    public Optional<Premio> porId(Integer id) {
        System.out.println("=====BUSQUEDA DE PREMIO POR ID====");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Premio premio) {
        try {
            System.out.println("=====GUARDAR PREMIO====");
            manager.getTransaction().begin();
            repository.guardar(premio);
            manager.getTransaction().commit();
//            System.out.println("=====PREMIO GUARDADO====");
        }catch (Exception e){
//            System.out.println("=====PREMIO NO GUARDADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Integer id) {
        try {
            System.out.println("=====EDITAR PREMIO====");
            manager.getTransaction().begin();
            repository.editar(id);
            manager.getTransaction().commit();
//            System.out.println("=====PREMIO EDITADO====");
        }catch (Exception e){
//            System.out.println("=====PREMIO NO EDITADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            System.out.println("=====ELIMINAR PREMIO====");
            manager.getTransaction().begin();
            repository.eliminar(id);
            manager.getTransaction().commit();
//            System.out.println("=====PREMIO ELIMINADO====");
        }catch (Exception e){
//            System.out.println("=====PREMIO NO ELIMINADO====");
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
