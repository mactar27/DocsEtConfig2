package sn.edu.isep.dbe.DocsEtConfig.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "magasin")
public class Magasin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
//    private String description;

    private String Telephone;

    @ManyToOne
    @JoinColumn(name = "cree_par",nullable=false)
    private User createur;

    @Override
    public String toString() {
        return "Magasin{" +
                "Telephone='" + Telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                ", nom='" + nom + '\'' +
                ", id=" + id +
                '}';
    }
}
