package com.servicepro.alpha.dto.requerimento;

import com.servicepro.alpha.domain.Horario;
import com.servicepro.alpha.domain.Sala;
import lombok.Data;

import java.util.List;

@Data
public class RequerimentoDTO {

    private Long id;
    private Sala sala;
    private Horario horarioInicial;
    private Horario horarioFinal;
    private String disciplina;
    private String numeroAluno;
    private String dia;
    private String blocoPreferido;
    private String tipoSala;
    private String status;
    private String registration;
    private List<String> equipment;
    private String observacoes;
    private String aprovadoPorquem;
    private String rejeitadoPorquem;


}
