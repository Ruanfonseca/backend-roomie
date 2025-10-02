package com.servicepro.alpha.repository;

import com.servicepro.alpha.domain.Report;
import com.servicepro.alpha.dto.relatorio.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

    List<Report> findByRange(ReportDTO dto);
}
