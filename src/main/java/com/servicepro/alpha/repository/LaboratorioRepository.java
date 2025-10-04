package com.servicepro.alpha.repository;

import com.servicepro.alpha.domain.Laboratorio;
import com.servicepro.alpha.domain.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio,Long> {
    Laboratorio findByNome(String nome);
}
