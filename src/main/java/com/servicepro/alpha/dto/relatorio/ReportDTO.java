package com.servicepro.alpha.dto.relatorio;

import lombok.Data;

import java.util.Date;

@Data
public class ReportDTO {
    private Date startDate;
    private Date endDate;
    private String reportType;
}
