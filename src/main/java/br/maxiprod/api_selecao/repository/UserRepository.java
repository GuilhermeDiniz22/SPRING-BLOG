package br.maxiprod.api_selecao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.maxiprod.api_selecao.models.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrUsername(String email, String username);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
