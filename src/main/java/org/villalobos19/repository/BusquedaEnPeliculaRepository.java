package org.villalobos19.repository;

import java.util.List;

public interface BusquedaEnPeliculaRepository extends BusquedaDistribuidoraRepository, BusquedaRecaudacionRepository {

     List<Object[]> peliculaPorNacion();

    List<Object[]> peliculaPorNacion(String nacionalidad);

    List<Object[]> peliculaConcatenandoAnyo();
    List<Object[]> peliculaConcatenandoAnyo(String nacion);
}
