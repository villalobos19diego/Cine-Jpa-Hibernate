package org.villalobos19.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.villalobos19.entity.Actor;
import org.villalobos19.entity.Participa;


import java.util.List;

public class ParticipaRepository implements CrudRepository<Participa>, FindIdRepository<Participa> , BusquedaActorRepository{

    private final EntityManager manager;

    public ParticipaRepository(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public List<Participa> listar() {
        return manager.createQuery("SELECT p FROM Participa p", Participa.class).getResultList();
    }

    @Override
    public Participa porId(Integer id) {
        return manager.find(Participa.class, id);
    }


    @Override
    public void guardar(Participa participa) {
        if (participa.getPelicula() != null && participa.getActor() != null) {
            manager.merge(participa);
        } else {
            manager.persist(participa);
        }
    }

    @Override
    public void editar(Integer id) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void eliminar(Integer id,TipoBusqueda busqueda) {
        TypedQuery query = null;
        if (busqueda == TipoBusqueda.POR_ACTOR) {
            System.out.println("=====ELIMINAR ID ACTOR====");
            query = manager.createQuery(
                    "DELETE  FROM Participa pa WHERE pa.actor.id=:id  ",
                    Participa.class
            );
        }
        if (busqueda == TipoBusqueda.POR_PELICULA) {
            System.out.println("=====ELIMINAR  ID PELICULA====");
            query = manager.createQuery(
                    "DELETE  FROM Participa pa WHERE pa.pelicula.id=:id  ",
                    Participa.class
            );
        }
        query.setParameter("id", id);
    }

    @Override
    public void eliminarUnique(Integer id1, Integer id2) {

            System.out.println("=====BUSQUEDA DE PARTICIPA POR ID PELICULA====");
            Query query = manager.createQuery(
                    "SELECT pa FROM Participa pa WHERE pa.pelicula.id=:id1 AND pa.actor.id=:id2",
                    Participa.class
            );
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        Participa participa= (Participa) query.getSingleResult();
        manager.remove(participa);
    }


    @Override
    public List<Participa> listarPorId(Integer id, TipoBusqueda busqueda) {
        TypedQuery query = null;
        if (busqueda == TipoBusqueda.POR_ACTOR) {
            System.out.println("=====BUSQUEDA DE PARTICIPA POR ID ACTOR====");
            query = manager.createQuery(
                    "SELECT pa FROM Participa pa INNER JOIN" +
                            " pa.pelicula p WHERE pa.actor.id = :id",
                    Participa.class
            );
        } else if (busqueda == TipoBusqueda.POR_PELICULA) {
            System.out.println("=====BUSQUEDA DE PARTICIPA POR ID PELICULA====");
            query = manager.createQuery(
                    "SELECT pa FROM Participa pa INNER JOIN" +
                            " pa.actor p WHERE pa.pelicula.id = :id",
                    Participa.class
            );
        }
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void porId(Integer id, TipoBusqueda tipoBusqueda) {

    }

    @Override
    public List<Actor> actorListDeadPorNacion(String nacion) {
        return null;
    }

    @Override
    public List<Object[]> actorMayorParticipacion() {
        Query query = manager.createQuery("SELECT pa.actor,count(pa)FROM Participa pa GROUP BY pa.actor ORDER BY count(pa) DESC ");
        query.setMaxResults(1);
        List<Object[]> results = query.getResultList();
        return results;
    }
}

