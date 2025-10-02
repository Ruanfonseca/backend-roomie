package com.servicepro.alpha.repository;

import com.servicepro.alpha.domain.Horario;
import com.servicepro.alpha.domain.Requerimento;
import com.servicepro.alpha.domain.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequerimentoRepository extends JpaRepository<Requerimento,Long> {
    Requerimento findByToken(String token);

    Requerimento findBySalaReq(Sala sala,Horario horarioinicial ,Horario horarioFinal,String dia);
}
