package org.villalobos19.repository;

import java.util.List;

public interface CrudRepository <T>{
    List<T> listar();
    T porId(Integer id);
    void guardar(T t);
    void editar(Integer id);
    void eliminar(Integer id);
}
