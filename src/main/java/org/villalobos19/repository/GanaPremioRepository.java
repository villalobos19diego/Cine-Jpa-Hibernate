package org.villalobos19.repository;

import jakarta.persistence.EntityManager;
import org.villalobos19.entity.GanaPremio;

import java.util.List;

public class GanaPremioRepository implements CrudRepository<GanaPremio>{
    private final EntityManager manager;

    public GanaPremioRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<GanaPremio> listar() {
        return manager.createQuery("SELECT g FROM GanaPremio g", GanaPremio.class).getResultList();
    }

    @Override
    public GanaPremio porId(Integer id) {
        return manager.find(GanaPremio.class,id);
    }

    @Override
    public void guardar(GanaPremio ganaPremio) {
        if (ganaPremio.getPremio()!=null
                && ganaPremio.getPelicula()!=null
                && ganaPremio.getAnyo()!=null ){
            manager.merge(ganaPremio);
        }else {
            manager.persist(ganaPremio);
        }
    }

    @Override
    public void editar(Integer id) {

    }

    @Override
    public void eliminar(Integer id) {
        GanaPremio premio = porId(id);
        manager.remove(premio);
    }
}
