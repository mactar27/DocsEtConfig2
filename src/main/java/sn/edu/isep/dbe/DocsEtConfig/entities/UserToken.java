package sn.edu.isep.dbe.DocsEtConfig.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_token")
public class UserToken {
    @Id
    @Column(name = "token", nullable = false)
    private String token;

    @ManyToOne
    private User user;
    private Date notBefore;
    private Date expiresAt;

}
