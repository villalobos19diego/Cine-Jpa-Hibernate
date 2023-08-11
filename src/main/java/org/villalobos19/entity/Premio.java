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
@Table(name = "premios")
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codPremio")
    private Integer id;

    @Column(nullable = false,name = "Premio ")
    private String premio;

    @Override
    public String toString() {
        return "Premio:" + premio;
    }
}
