package org.villalobos19.service;

import org.villalobos19.entity.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    List<Director> listar();
    Optional<Director> porId(Integer id);
    void guardar(Director director);
    void editar(Integer id);
    void eliminar(Integer id);
    List<Object[]> directorParticipaciones(Long participaciones);
    List<Director>  directorSinParticipaciones();
}
