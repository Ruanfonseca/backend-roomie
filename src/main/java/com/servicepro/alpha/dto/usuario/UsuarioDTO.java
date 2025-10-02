package com.servicepro.alpha.dto.usuario;

import com.servicepro.alpha.enums.Role;
import lombok.Data;

@Data
public class UsuarioDTO {
    private String name;
    private String email;
    private String password;
    private String departamento;
    private String matricula;
    private Role role;
}
