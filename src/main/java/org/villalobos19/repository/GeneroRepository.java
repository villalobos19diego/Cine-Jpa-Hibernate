package org.villalobos19.repository;

import jakarta.persistence.EntityManager;
import org.villalobos19.entity.Genero;

import javax.swing.*;
import java.util.List;

public class GeneroRepository implements CrudRepository<Genero>{
    private final EntityManager manager;

    public GeneroRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Genero> listar() {
        return manager.createQuery("SELECT g FROM Genero g", Genero.class).getResultList();
    }

    @Override
    public Genero porId(Integer id) {
        return manager.find(Genero.class,id);
    }

    @Override
    public void guardar(Genero genero) {
        if (genero.getId()!=null && genero.getId()>0){
            manager.merge(genero);
        }else{
            manager.persist(genero);
        }
    }

    @Override
    public void editar(Integer id) {
        Genero genero= porId(id);
        if(genero.getId()!=null && genero.getId()>0){
            genero.setNombre(JOptionPane.showInputDialog("Ingrese Genero de pelicula: ",genero.getNombre()));
            manager.merge(genero);
        }
    }

    @Override
    public void eliminar(Integer id) {
        Genero genero = porId(id);
        manager.remove(genero);
    }
}
