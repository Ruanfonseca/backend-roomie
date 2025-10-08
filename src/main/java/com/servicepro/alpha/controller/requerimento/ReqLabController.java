package com.servicepro.alpha.controller.requerimento;

import com.servicepro.alpha.domain.Professor;
import com.servicepro.alpha.domain.Requerimento;
import com.servicepro.alpha.domain.RequerimentoLaboratorio;
import com.servicepro.alpha.dto.requerimento.RequerimentoDTO;
import com.servicepro.alpha.dto.requerimento.RequerimentoResponseDTO;
import com.servicepro.alpha.dto.requerimentoLab.RequerimentoLabDTO;
import com.servicepro.alpha.dto.requerimentoLab.RequerimentoLabResponseDTO;
import com.servicepro.alpha.repository.ProfessorRepository;
import com.servicepro.alpha.service.RequerimentoLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitations/lab")
public class ReqLabController {

    @Autowired
    RequerimentoLabService service;

    @Autowired
    ProfessorRepository professorRepository;


    @GetMapping
    public ResponseEntity<?> getLabReqs() {

        try {
            List<RequerimentoLaboratorio> reqs = service.obterTodosRequerimentos();
            return ResponseEntity.ok(reqs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar requerimentos .");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RequerimentoLabDTO req) {

        try {
            // Verifica se já existe um professor cadastrado
            Professor profExiste = professorRepository.findByMatricula(req.getMatriculaDocente());

            if (profExiste == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Não existe professor com a matrícula " + req.getMatriculaDocente());
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
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody RequerimentoLabResponseDTO dto) {
        try {
            RequerimentoLaboratorio req = service.atualizarReq(id, dto);
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
    public ResponseEntity<?> deleteReqLab(@PathVariable String id) {
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

//    @PostMapping("/baixa")
//    public ResponseEntity<?> baixaRequerimento(@RequestBody RequerimentoLabDTO dto) {
//        try {
//            RequerimentoLaboratorio reqExistente = service.buscarPorId(dto.getId());
//
//            if (reqExistente == null) {
//                return ResponseEntity
//                        .status(HttpStatus.CONFLICT)
//                        .body("Esse requerimento não existe");
//            }
//
//            service.darBaixa(dto);
//
//            return ResponseEntity.ok().build();
//
//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erro no servidor.");
//        }
//
//    }

    @PostMapping("/search/token")
    public ResponseEntity<?> buscarPorToken(@RequestBody String token) {
        try {
            // Verificando se já existe um professor cadastrado
            RequerimentoLaboratorio reqExistente = service.buscarToken(token);

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

//    @GetMapping("/agenda/lab")
//    public ResponseEntity<?> getBuscaParaAgenda() {
//
//        try {
//            List<RequerimentoLabResponseDTO> reqsAgenda = service.buscandoParaAgenda();
//            return ResponseEntity.ok(reqsAgenda);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erro ao buscar requerimentos para tela de agenda .");
//        }
//    }
}
