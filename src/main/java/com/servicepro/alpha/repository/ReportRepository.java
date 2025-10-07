package com.servicepro.alpha.repository;

import com.servicepro.alpha.domain.Report;
import com.servicepro.alpha.domain.Requerimento;
import com.servicepro.alpha.domain.RequerimentoLaboratorio;
import com.servicepro.alpha.dto.relatorio.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Requerimento r " +
            "WHERE r.dia BETWEEN :#{#dto.startDate} AND :#{#dto.endDate}")
    List<Requerimento> findByRangeSala(@Param("dto") ReportDTO dto);

    @Query("SELECT r FROM RequerimentoLaboratorio r " +
            "WHERE r.dia BETWEEN :#{#dto.startDate} AND :#{#dto.endDate}")
    List<RequerimentoLaboratorio> findByRangeLab(@Param("dto") ReportDTO dto);
}

