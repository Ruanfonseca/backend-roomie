package com.servicepro.alpha.controller.professor;

import com.servicepro.alpha.domain.Professor;
import com.servicepro.alpha.dto.professor.ProfessorDTO;
import com.servicepro.alpha.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    public ResponseEntity<?> getTeachers() {
        try {
            List<Professor> professores = service.buscarProfessor();
            return ResponseEntity.ok(professores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar professores.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createProfessor(@RequestBody ProfessorDTO dto) {
        try {
            // Verificando se já existe um professor cadastrado
            Professor professorExistente = service.buscarProfessorPorMatricula(dto.getRegisterNumber());

            if (professorExistente != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Professor com matrícula já existente.");
            }

            service.salvarProfessor(dto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar professor.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ProfessorDTO dto) {
        System.out.println("📝 [UPDATE PROFESSOR] Iniciando atualização do professor...");
        System.out.println("📦 ID recebido: " + id);
        System.out.println("📤 Dados recebidos (DTO): " + dto);

        try {
            Professor professor = service.atualizarProfessor(id, dto);

            if (professor == null) {
                System.out.println("⚠️ Nenhum professor encontrado com o ID: " + id);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Professor não encontrado.");
            }

            System.out.println("✅ Professor atualizado com sucesso: " + professor);
            return ResponseEntity.ok(professor);

        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar professor com ID: " + id);
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar professor: " + e.getMessage());
        } finally {
            System.out.println("🔚 [UPDATE PROFESSOR] Finalizando requisição de atualização.\n");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            boolean deletado = service.deletarProfessor(id);
            if (!deletado) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Professor não encontrado.");
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar professor.");
        }
    }
}
