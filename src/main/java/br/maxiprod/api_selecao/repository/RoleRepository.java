package br.maxiprod.api_selecao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.maxiprod.api_selecao.models.Role;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
