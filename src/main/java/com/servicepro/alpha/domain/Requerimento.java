package com.servicepro.alpha.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "requerimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Requerimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @OneToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;


    private String disciplina;
    private String numeroAluno;
    private String blocoPreferido;
    private String tipoSala;
    private String equipamentoNecessario;
    private String observacoes;
}
