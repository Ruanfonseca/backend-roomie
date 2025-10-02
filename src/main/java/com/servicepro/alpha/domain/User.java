package com.servicepro.alpha.domain;

import com.servicepro.alpha.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String email;
    private String password;
    private String departamento;
    private String matricula;
    private Role role;
    private LocalDate updatedAt;
    private LocalDate createdAt;

}
