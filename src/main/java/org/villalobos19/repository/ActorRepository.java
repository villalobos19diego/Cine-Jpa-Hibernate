package org.villalobos19.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.villalobos19.entity.Actor;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActorRepository implements CrudRepository<Actor>,BusquedaActorRepository{
    private final EntityManager manager;

    public ActorRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Actor> listar() {
        return manager.createQuery("SELECT a FROM Actor a", Actor.class).getResultList();
    }

    @Override
    public Actor porId(Integer id) {
        return manager.find(Actor.class,id);
    }

    @Override
    public void guardar(Actor actor) {
        if (actor.getId()!=null && actor.getId()>0){
            manager.merge(actor);
        }else {
            manager.persist(actor);
        }
    }

    @Override
    public void editar(Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Actor actor = porId(id);
        if(actor.getId()!=null && actor.getId()>0){
            actor.setNombre(JOptionPane.showInputDialog("Ingrese el nombre: ",actor.getNombre()));
            actor.setNacionalidad(JOptionPane.showInputDialog("Ingrese la nacionalidad: ",actor.getNacionalidad()));
            String fechaDeNacimientoInput = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento (yyyy/MM/dd):",actor.getFechaNacimiento());
            actor.setLugarNacimiento(JOptionPane.showInputDialog("Ingrese el lugar de nacimiento: ",actor.getLugarNacimiento()));
            String fechaDefuncionInput = JOptionPane.showInputDialog("Ingrese la fecha de defuncion (yyyy/MM/dd):",actor.getFechaMuerte());
            if (!fechaDefuncionInput.isEmpty()){
                LocalDate fechaDefuncion = LocalDate.parse(fechaDefuncionInput, formatter);
                actor.setFechaMuerte(fechaDefuncion.atStartOfDay());
            }
            if (!fechaDeNacimientoInput.isEmpty()){
                LocalDate fechaDeNacimiento = LocalDate.parse(fechaDeNacimientoInput, formatter);
                actor.setFechaNacimiento(fechaDeNacimiento.atStartOfDay());
            }
            actor.setLugarMuerte(JOptionPane.showInputDialog("Ingrese el lugar de fallecimiento: ",actor.getLugarMuerte()));
            manager.merge(actor);
        }
    }

    @Override
    public void eliminar(Integer id) {
        Actor actor = porId(id);
        manager.remove(actor);
    }

    @Override
    public List<Actor> actorListDeadPorNacion(String nacionalidad) {
        Query query = manager.createQuery("SELECT a FROM Actor a WHERE a.nacionalidad = :nacionalidad AND a.fechaMuerte IS NOT NULL");
        query.setParameter("nacionalidad", nacionalidad);
        List<Actor> actoresFallecidos = query.getResultList();
        return actoresFallecidos;
    }




    @Override
    public List<Object[]> actorMayorParticipacion() {
       return null;
    }

}
