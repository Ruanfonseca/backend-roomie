package com.servicepro.alpha.repository;

import com.servicepro.alpha.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {

    Professor findByMatricula(String matricula);
}
