package sn.edu.isep.dbe.DocsEtConfig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isep.dbe.DocsEtConfig.entities.AuditRequest;

public interface AuditRequestRepository extends JpaRepository<AuditRequest, Integer> {
}
