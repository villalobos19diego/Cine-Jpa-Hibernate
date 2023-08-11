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
@Table(name = "actores")

public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodActor")
    private Integer id;

    @Column(nullable = false,name = "Nombre")
    private String nombre;

    @Column(name = "FNacimiento")
    private LocalDateTime fechaNacimiento;
    @Column(name = "LNacimiento")
    private String lugarNacimiento;

    @Column(name = "Nacionalidad")
    private String nacionalidad;

    @Column(name = "FMuerte")
    private LocalDateTime fechaMuerte;
    @Column(name = "LMuerte")
    private String lugarMuerte;


    @Override
    public String toString() {
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        String formattedFechaNacimiento = (fechaNacimiento != null) ? fechaNacimiento.format(dtformat) : null;
        String formattedFechaMuerte = (fechaMuerte != null) ? fechaMuerte.format(dtformat) : null;
        return "id: "+id+
                ", nombre: " + nombre+
                ", fechaNacimiento: " + formattedFechaNacimiento +
                ", lugarNacimiento: " + lugarNacimiento +
                ", nacionalidad: " + nacionalidad +
                ", fechaMuerte: " + formattedFechaMuerte +
                ", lugarMuerte: " + lugarMuerte;
    }
}
