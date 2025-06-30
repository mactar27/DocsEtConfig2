package sn.edu.isep.dbe.DocsEtConfig.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.edu.isep.dbe.DocsEtConfig.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNom(String nom);
}
