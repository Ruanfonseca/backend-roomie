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
    @JoinColumn(name = "horario_inicial_id")
    private Horario horarioInicial;

    @OneToOne
    @JoinColumn(name = "horario_final_id")
    private Horario horarioFinal;


    private String disciplina;
    private String numeroAluno;
    private String dia;

    private String token;
    private String blocoPreferido;
    private String tipoSala;
    private String registration;

    @ElementCollection
    @CollectionTable(name = "equipamentos", joinColumns = @JoinColumn(name = "equipamento_id"))
    @Column(name = "equipamento")
    private List<String> equipamentoNecessario = new ArrayList<>();

    private String observacoes;
    private String status;
    private String aprovadoPorquem;
    private String rejeitadoPorquem;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
