package org.villalobos19.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "peliculas")

public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodPelicula")
    private Integer id;

    @Column(nullable = false, name = "Titulo")
    private String titulo;
    @Column(nullable = false, name = "Anyo")
    private String anyo;

    @Column(name = "Nacionalidad")
    private String nacionalidad;
    @Column(name = "Duracion")
    private Float duracion;
    @Column(name = "FechaEstreno")
    private LocalDateTime fechaEstreno;
    @Column(name = "Taquilla")
    private Double taquilla;
    @Column(name = "Productora")
    private String productora;
    @Column(name = "Distribuidora")
    private String distribuidora;

    @OneToOne
    @JoinColumn(name = "Genero")
    private Genero genero;

    @OneToOne
    @JoinColumn(name = "Director")
    private  Director director;

    @Override
    public String toString() {
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Id: "+id +
                ", Titulo: " + titulo +
                ", anyo: " + anyo +
                ", nacionalidad: " + nacionalidad +
                ", duracion: " +duracion +
                ", fechaEstreno: " + fechaEstreno.format(dtformat) +
                ", taquilla: " + taquilla +
                ", productora: " + productora +
                ", Distribuidora: " + distribuidora +
                ", genero: " + genero +
                ", director: " + director;
    }
}
