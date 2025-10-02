package com.servicepro.alpha.repository;
import com.servicepro.alpha.domain.Horario;
import org.springframework.stereotype.Repository;

import com.servicepro.alpha.domain.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario,Long> {

    Horario findByNome(String nome);
}
