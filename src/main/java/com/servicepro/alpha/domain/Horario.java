package com.servicepro.alpha.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "horario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // alterado para Long (mais comum para ID auto gerado)

    private String nome;
    private String horarioInicial;
    private String horarioFinal;

    @ElementCollection
    @CollectionTable(name = "horario_dias", joinColumns = @JoinColumn(name = "horario_id"))
    @Column(name = "dia")
    private List<String> dias = new ArrayList<>(); // substituído String[] por List<String>

    private String semestre;
    private String status;
    private String descricao;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
