package com.servicepro.alpha.dto.horario;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HorarioDTO {

    private String nome;
    private String horarioInicial;
    private String horarioFinal;
    private List<String> dias;
    private String semestre;
    private String status;
    private String descricao;
    private String createdAt;
    private String updatedAt;
}
