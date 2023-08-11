package org.villalobos19.service;


import org.villalobos19.entity.Premio;

import java.util.List;
import java.util.Optional;

public interface PremioService {
    List<Premio> listar();
    Optional<Premio> porId(Integer id);
    void guardar(Premio premio);
    void editar(Integer id);
    void eliminar(Integer id);
}
