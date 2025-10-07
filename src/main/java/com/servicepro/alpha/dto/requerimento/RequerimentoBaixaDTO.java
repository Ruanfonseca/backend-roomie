package com.servicepro.alpha.dto.requerimento;

import com.servicepro.alpha.domain.Sala;
import lombok.Data;

@Data
public class RequerimentoBaixaDTO extends RequerimentoDTO {
    private Long id;
    private Sala room;
    private String observations;
    private String status;
    private String approvedBy;
    private String rejectionReason;
    private String updatedAt;
}
