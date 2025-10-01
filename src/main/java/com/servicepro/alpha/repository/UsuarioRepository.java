package com.servicepro.alpha.repository;

import com.servicepro.alpha.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<User,String> {

    User findByEmail(String email);
}
