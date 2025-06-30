package sn.edu.isep.dbe.DocsEtConfig.entities.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String email;
    private String prenom;
    private String nom;
    private String token;
    private List<String> roles;
    private List<String> droits;
    private Date dateExpiration;
    private Date notBefore;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "email='" + email + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", token='" + token + '\'' +
                ", roles=" + roles +
                ", droits=" + droits +
                '}';
    }
}