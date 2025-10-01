package com.servicepro.alpha.dto.professor;

import lombok.Data;

@Data
public class ProfessorDTO {
    String name;
    String email;
    String phone;
    String department;
    String status;
    Integer totalRequests;
    String approvedRequests;
    String registerNumber;

}
