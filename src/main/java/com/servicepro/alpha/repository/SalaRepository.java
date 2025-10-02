package com.servicepro.alpha.repository;

import com.servicepro.alpha.domain.Professor;
import com.servicepro.alpha.domain.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala,Long> {
    Sala findByName(String nome);
}
