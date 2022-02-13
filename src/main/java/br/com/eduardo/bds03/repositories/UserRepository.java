package br.com.eduardo.bds03.repositories;

import br.com.eduardo.bds03.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
