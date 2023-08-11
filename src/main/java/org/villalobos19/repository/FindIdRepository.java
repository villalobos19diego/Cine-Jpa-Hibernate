package org.villalobos19.repository;

import java.util.List;

public interface FindIdRepository <T>{
    List<T> listarPorId(Integer id,TipoBusqueda tipo);
    void porId(Integer id,TipoBusqueda tipoBusqueda);
    void eliminar(Integer id,TipoBusqueda tipoBusqueda);
    void eliminarUnique(Integer id1,Integer id2);

}
