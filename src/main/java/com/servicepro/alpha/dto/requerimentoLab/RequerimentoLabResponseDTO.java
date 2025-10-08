package com.servicepro.alpha.dto.requerimentoLab;

import com.servicepro.alpha.domain.Horario;
import com.servicepro.alpha.domain.Laboratorio;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequerimentoLabResponseDTO extends RequerimentoLabDTO{
    private Long id;
    private Laboratorio laboratorio;
    private String token;
    private String aprovadoPorquem;
    private String rejeitadoPorquem;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
