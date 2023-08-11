package org.villalobos19.repository;

import jakarta.persistence.EntityManager;
import org.villalobos19.entity.Premio;

import java.util.List;

public class PremioRespository implements CrudRepository<Premio >{
    private final EntityManager manager;

    public PremioRespository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Premio> listar() {
        return manager.createQuery("SELECT p FROM Premio p", Premio.class).getResultList();
    }

    @Override
    public Premio porId(Integer id) {
        return manager.find(Premio.class,id);
    }

    @Override
    public void guardar(Premio premio) {
        if (premio.getId()!=null && premio.getId()>0){
            manager.merge(premio);
        }else {
            manager.persist(premio);
        }
    }

    @Override
    public void editar(Integer id) {

    }

    @Override
    public void eliminar(Integer id) {
        Premio premio= porId(id);
        manager.remove(premio);
    }
}
