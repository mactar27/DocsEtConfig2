package sn.edu.isep.dbe.DocsEtConfig.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String adresse;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "adresse='" + adresse + '\'' +
                ", droits=" + droits +
                ", roles=" + roles +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", id=" + id +
                '}';
    }

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Droit> droits;

}