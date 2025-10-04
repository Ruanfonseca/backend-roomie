package com.servicepro.alpha.dto.requerimentoLab;

import com.servicepro.alpha.domain.Horario;
import com.servicepro.alpha.domain.Laboratorio;
import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequerimentoLabDTO {
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
    private String numeroGruposAlunos;
    private String nomeTecnicoLaboratorista;
    private String status;

    private Horario horarioInicio;


    private Horario horarioFinal;


    private Laboratorio laboratorio;

    private String numeroAluno;

    private List<UtilitariosDTO> utilitarios = new ArrayList<>();

    private String aprovadoPorquem;
    private String rejeitadoPorquem;
}
