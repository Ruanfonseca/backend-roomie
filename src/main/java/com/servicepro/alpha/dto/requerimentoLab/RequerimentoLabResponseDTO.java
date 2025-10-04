package com.servicepro.alpha.dto.requerimentoLab;

import com.servicepro.alpha.domain.Horario;
import com.servicepro.alpha.domain.Laboratorio;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequerimentoLabResponseDTO {
    private Long id;
    private String nomeDocente;
    private String matriculaDocente;
    private String emailDocente;
    private String disciplina;
    private String curso;
    private String unidadeAcademica;
    private String tituloAula;
    private String dia;
    private Boolean presencaTecnicoLaboratorista;
    private String token;
    private String time;
    private String numeroGruposAlunos;
    private String nomeTecnicoLaboratorista;

    private Horario horarioInicio;


    private Horario horarioFinal;


    private Laboratorio laboratorio;

    private Integer numeroAluno;

    private String aprovadoPorquem;
    private String status;
    private String rejeitadoPorquem;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<UtilitariosDTO> utilitarios = new ArrayList<>();
}
