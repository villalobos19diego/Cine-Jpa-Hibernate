package org.villalobos19.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.villalobos19.entity.Pelicula;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PeliculaRepository implements CrudRepository<Pelicula>,CambioMonedaRepository, BusquedaEnPeliculaRepository {

    private final EntityManager manager;

    public PeliculaRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Pelicula> listar() {
        return manager.createQuery("SELECT p FROM Pelicula p", Pelicula.class).getResultList();
    }

    @Override
    public Pelicula porId(Integer id) {
        return manager.find(Pelicula.class,id);
    }

    @Override
    public void guardar(Pelicula pelicula) {
        if (pelicula.getId()!=null && pelicula.getId()>0){
            manager.merge(pelicula);
        }else {
            manager.persist(pelicula);
        }
    }

    @Override
    public void editar(Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Pelicula pelicula = porId(id);
        if (pelicula.getId() != null && pelicula.getId() > 0) {
            pelicula.setDistribuidora(JOptionPane.showInputDialog("Ingrese la distribuidora: ", pelicula.getDistribuidora()));
            pelicula.setNacionalidad(JOptionPane.showInputDialog("Ingrese la nacionalidad: ", pelicula.getNacionalidad()));
            String fechaDeEstrenoInput = JOptionPane.showInputDialog("Ingrese la fecha de estreno (yyyy/MM/dd):", pelicula.getFechaEstreno());
            if (!fechaDeEstrenoInput.isEmpty()) {
                LocalDate fechaDeEstreno = LocalDate.parse(fechaDeEstrenoInput, formatter);
                pelicula.setFechaEstreno(fechaDeEstreno.atStartOfDay());
            }
            pelicula.setAnyo(JOptionPane.showInputDialog("Ingrese el anyo: ", pelicula.getAnyo()));
            pelicula.setDuracion(Float.valueOf(JOptionPane.showInputDialog("Ingrese la duracion de la pelicula: ", pelicula.getDuracion())));
            pelicula.setTaquilla(Double.valueOf(JOptionPane.showInputDialog("Ingrese la taquilla recaudada: ", pelicula.getTaquilla())));
            pelicula.setProductora(JOptionPane.showInputDialog("Ingrese la productora: ", pelicula.getProductora()));
            pelicula.setDistribuidora(JOptionPane.showInputDialog("Ingrese la distribuidora: ", pelicula.getDistribuidora()));

            manager.merge(pelicula);
        }
    }

    @Override
    public void eliminar(Integer id) {
        Pelicula pelicula = porId(id);
        manager.remove(pelicula);
    }

    @Override
    public void cambiarMoneda(TipoMoneda moneda) {

    }

    @Override
    public void cambiarMonedaPorPais(Pais pais, TipoMoneda moneda) {
        Query query = manager.createQuery("UPDATE Pelicula p SET p.taquilla = p.taquilla * :factorConversion WHERE p.nacionalidad = :pais");
        query.setParameter("factorConversion", moneda.getValor());
        query.setParameter("pais", pais.getNacion());
        query.executeUpdate();
    }

    @Override
    public String distribuidora(String pelicula) {
        TypedQuery<Pelicula> query = manager.createQuery("SELECT p FROM Pelicula p WHERE p.titulo = :pelicula", Pelicula.class);
        query.setParameter("pelicula", pelicula);
        Pelicula peliculaEncontrada = query.getSingleResult();

        if (peliculaEncontrada != null) {
            return peliculaEncontrada.getDistribuidora();
        } else {
            return null;
        }
    }


    @Override
    public List<Object[]> peliculaPorNacion() {
        Query query = manager.createQuery("SELECT p.nacionalidad, COUNT(p) FROM Pelicula p  GROUP BY p.nacionalidad");
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }

    @Override
    public List<Object[]> peliculaPorNacion(String nacionalidad) {
        Query query = manager.createQuery("SELECT p.nacionalidad, COUNT(p) FROM Pelicula p WHERE p.nacionalidad = :nacionalidad GROUP BY p.nacionalidad");
        query.setParameter("nacionalidad", nacionalidad);
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }


    @Override
    public List<Object[]> peliculaConcatenandoAnyo() {
        Query query = manager.createQuery("SELECT p.titulo, p.anyo,CONCAT(p.titulo, ' (', p.anyo, ')'),p.nacionalidad FROM Pelicula p");
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }

    @Override
    public List<Object[]> peliculaConcatenandoAnyo(String nacion) {
        Query query = manager.createQuery("SELECT p.titulo, p.anyo,CONCAT(p.titulo, ' (', p.anyo, ')'),p.nacionalidad FROM Pelicula p WHERE p.nacionalidad=:nacion");
        query.setParameter("nacion",nacion);
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }


    @Override
    public List<Object[]> recaudacionPeliculasNacion(String nacion) {
        Query query = manager.createQuery("SELECT p.nacionalidad, SUM(p.taquilla) FROM Pelicula p WHERE p.nacionalidad = :nacion GROUP BY p.nacionalidad");
        query.setParameter("nacion", nacion);
        List<Object[]> results = query.getResultList();
        return results;
    }


}
