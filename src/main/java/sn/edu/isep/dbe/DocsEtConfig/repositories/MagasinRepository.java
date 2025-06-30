package sn.edu.isep.dbe.DocsEtConfig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isep.dbe.DocsEtConfig.entities.Magasin;

import java.util.Optional;

public interface MagasinRepository extends JpaRepository<Magasin, Integer> {

    Optional<Magasin> findByNom(String nom);
}
