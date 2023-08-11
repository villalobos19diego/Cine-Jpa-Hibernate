package org.villalobos19.service;


import org.villalobos19.entity.Genero;

import java.util.List;
import java.util.Optional;

public interface GeneroService {
    List<Genero> listar();
    Optional<Genero> porId(Integer id);
    void guardar(Genero genero);
    void editar(Integer id);
    void eliminar(Integer id);
}
