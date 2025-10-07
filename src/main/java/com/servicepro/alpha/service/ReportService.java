package com.servicepro.alpha.service;

import com.servicepro.alpha.domain.Report;
import com.servicepro.alpha.domain.Requerimento;
import com.servicepro.alpha.domain.RequerimentoLaboratorio;
import com.servicepro.alpha.domain.Sala;
import com.servicepro.alpha.dto.relatorio.ReportDTO;
import com.servicepro.alpha.repository.ReportRepository;
import com.servicepro.alpha.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {


    @Autowired
    private ReportRepository repository;

    public List<Report> buscarRelatorio(ReportDTO dto) {
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            return null;
        }

        if ("Lab".equalsIgnoreCase(dto.getRequerimentsType())) {
            List<RequerimentoLaboratorio> labs = repository.findByRangeLab(dto);
            dto.setRequerimentoLaboratorios(labs);
        }

        else if ("Sala".equalsIgnoreCase(dto.getRequerimentsType())) {
            List<Requerimento> salas = repository.findByRangeSala(dto);
            dto.setRequerimentos(salas);
        }

        return null;
    }



}
