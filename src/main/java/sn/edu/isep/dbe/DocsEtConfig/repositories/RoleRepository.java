package sn.edu.isep.dbe.DocsEtConfig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isep.dbe.DocsEtConfig.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}