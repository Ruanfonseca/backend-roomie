package com.servicepro.alpha.controller.requerimento;

import com.servicepro.alpha.domain.Requerimento;
import com.servicepro.alpha.domain.Sala;
import com.servicepro.alpha.dto.requerimento.RequerimentoBaixaDTO;
import com.servicepro.alpha.dto.requerimento.RequerimentoDTO;
import com.servicepro.alpha.dto.requerimento.RequerimentoResponseDTO;
import com.servicepro.alpha.dto.sala.SalaDTO;
import com.servicepro.alpha.service.RequerimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitations")
public class ReqController {

    @Autowired
    RequerimentoService service;

    @GetMapping
    public ResponseEntity<?> getReqs() {

        try {
            List<Requerimento> reqs = service.obterTodosRequerimentos();
            return ResponseEntity.ok(reqs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar requerimentos .");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RequerimentoDTO req) {
        try {
            // Verificando se já existe um professor cadastrado
            Requerimento reqExistente = service.buscarRequerimento(req.getDia(),req.getScheduleInitial(),req.getScheduleEnd());

            if (reqExistente != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Já existe um requerimento cadastrado com essa sala no dia solicitado.");
            }

            service.salvarReq(req);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar requerimento.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody RequerimentoDTO dto) {
        try {
            Requerimento req = service.atualizarReq(id, dto);
            if (req == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Requerimento não encontrado.");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar requerimento.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReq(@PathVariable String id) {
        try {
            boolean deletado = service.delete(id);
            if (!deletado) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Requerimento não encontrado.");
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar requerimento.");
        }
    }

    @PostMapping("/baixa")
    public ResponseEntity<?> baixaRequerimento(@RequestBody RequerimentoBaixaDTO dto) {
        try {
            Requerimento reqExistente = service.buscarPorId(dto.getId());

            if (reqExistente == null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Esse requerimento não existe");
            }

            service.darBaixa(reqExistente);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no servidor.");
        }

    }

    @PostMapping("/search/token")
    public ResponseEntity<?> buscarPorToken(@RequestBody String token) {
        try {
            // Verificando se já existe um professor cadastrado
            Requerimento reqExistente = service.buscarToken(token);

            if (reqExistente == null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Requerimento não existe");
            }

            return ResponseEntity.ok(reqExistente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping("/agenda")
//    public ResponseEntity<?> getBuscaParaAgenda() {
//
//        try {
//            List<RequerimentoResponseDTO> reqsAgenda = service.buscandoParaAgenda();
//            return ResponseEntity.ok(reqsAgenda);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erro ao buscar requerimentos para tela de agenda .");
//        }
//    }
}

