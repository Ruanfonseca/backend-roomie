package com.servicepro.alpha.repository;

import com.servicepro.alpha.domain.Horario;
import com.servicepro.alpha.domain.Laboratorio;
import com.servicepro.alpha.domain.RequerimentoLaboratorio;
import com.servicepro.alpha.domain.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequerimentolabRepository extends JpaRepository<RequerimentoLaboratorio,Long> {

    RequerimentoLaboratorio findByLaboratorioReq(Sala sala, Horario horarioInicial, Horario horarioFinal, String dia);

    RequerimentoLaboratorio findByToken(String token);
}
