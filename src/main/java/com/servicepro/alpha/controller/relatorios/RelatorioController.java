package com.servicepro.alpha.controller.relatorios;

import com.servicepro.alpha.dto.relatorio.ReportDTO;
import com.servicepro.alpha.domain.Report;
import com.servicepro.alpha.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class RelatorioController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/filter")
    public ResponseEntity<List<Report>> getReports(@RequestBody ReportDTO dto) {
        try {
            List<Report> reports = reportService.buscarRelatorio(dto);
            return ResponseEntity.ok(reports);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
