package org.villalobos19.repository;

import org.villalobos19.entity.Director;

import java.util.List;

public interface BusquedaDirectorRepository {
    Director directorMayorParticipacion();
    List<Object[]> directorParticipaciones(Long participaciones);
    List<Director>  directorSinParticipaciones();
}
