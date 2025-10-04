package com.servicepro.alpha.controller.analise;

import com.servicepro.alpha.domain.Requerimento;
import com.servicepro.alpha.domain.RequerimentoLaboratorio;
import com.servicepro.alpha.dto.dataAnalise.DataAnailiseDTO;
import com.servicepro.alpha.service.RequerimentoLabService;
import com.servicepro.alpha.service.RequerimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/analise")
public class AnaliseController {

    @Autowired
    private RequerimentoService requimentoService;

    @Autowired
    private RequerimentoLabService requimentoLabService;

    @GetMapping
    public ResponseEntity<?> getAnalise() {
        try {
            // Busca todos os requerimentos
            List<Requerimento> requerimentoSala = requimentoService.obterTodosRequerimentos();
            List<RequerimentoLaboratorio> requerimentoLaboratorios = requimentoLabService.obterTodosRequerimentos();

            // Junta as listas
            long qtdTotal = requerimentoSala.size() + requerimentoLaboratorios.size();

            // Contagem por status (em inglês)
            long qtdApproved = requerimentoSala.stream()
                    .filter(r -> "approved".equalsIgnoreCase(r.getStatus()))
                    .count()
                    +
                    requerimentoLaboratorios.stream()
                            .filter(r -> "approved".equalsIgnoreCase(r.getStatus()))
                            .count();

            long qtdPending = requerimentoSala.stream()
                    .filter(r -> "pending".equalsIgnoreCase(r.getStatus()))
                    .count()
                    +
                    requerimentoLaboratorios.stream()
                            .filter(r -> "pending".equalsIgnoreCase(r.getStatus()))
                            .count();

            long qtdRejected = requerimentoSala.stream()
                    .filter(r -> "rejected".equalsIgnoreCase(r.getStatus()))
                    .count()
                    +
                    requerimentoLaboratorios.stream()
                            .filter(r -> "rejected".equalsIgnoreCase(r.getStatus()))
                            .count();

            long qtdCancelled = requerimentoSala.stream()
                    .filter(r -> "cancelled".equalsIgnoreCase(r.getStatus()))
                    .count()
                    +
                    requerimentoLaboratorios.stream()
                            .filter(r -> "cancelled".equalsIgnoreCase(r.getStatus()))
                            .count();

            // Preenche DTO
            DataAnailiseDTO dto = new DataAnailiseDTO();
            dto.setQtdApproved(qtdApproved);
            dto.setQtdPending(qtdPending);
            dto.setQtdRejected(qtdRejected);
            dto.setQtdCancelled(qtdCancelled);
            dto.setQtdTotal(qtdTotal);

            return ResponseEntity.ok(dto);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar dados da análise.");
        }
    }
}



