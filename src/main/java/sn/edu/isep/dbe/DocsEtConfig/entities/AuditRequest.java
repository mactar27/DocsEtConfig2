package sn.edu.isep.dbe.DocsEtConfig.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "audit_request")
public class AuditRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uri;
    private String method;
    private String ipAddress;
    private String userAgent;
    private Date dateRequest;
    private String navigateur;
    private Integer status;
}
