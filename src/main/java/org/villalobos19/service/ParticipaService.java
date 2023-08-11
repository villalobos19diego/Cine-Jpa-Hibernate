package org.villalobos19.service;

import org.villalobos19.entity.Participa;
import org.villalobos19.repository.TipoBusqueda;

import java.util.List;

public interface ParticipaService {
    List<Participa> listar();
    List listarPorId(Integer id, TipoBusqueda busqueda);
    void porId(Integer id, TipoBusqueda busqueda);
    void guardar(Participa participa);
    void editar(Integer id,TipoBusqueda tipoBusqueda);
    void eliminar(Integer id);
    void eliminarUnique(Integer id1,Integer id2);
    List<Object[]> actorMayorParticipacion();
}
