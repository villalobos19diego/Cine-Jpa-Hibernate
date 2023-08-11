package org.villalobos19.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ParticipaId.class)
@Table(name = "participa")
public class Participa {
    @Id
    @ManyToOne
    @JoinColumn(name = "CodPelicula")
    private Pelicula pelicula;
    @Id
    @ManyToOne
    @JoinColumn (name = "CodActor")
    private Actor actor;

    @Override
    public String toString() {
        return "Actor: "+actor.getNombre()+
                " Participa en " + pelicula.getTitulo();
    }
}
