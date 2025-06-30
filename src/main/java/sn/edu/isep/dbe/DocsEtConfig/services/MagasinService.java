package sn.edu.isep.dbe.DocsEtConfig.services;

import org.springframework.stereotype.Service;
import sn.edu.isep.dbe.DocsEtConfig.entities.Magasin;
import sn.edu.isep.dbe.DocsEtConfig.repositories.MagasinRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MagasinService {

    private final MagasinRepository magasinRepository;

    public MagasinService (MagasinRepository magasinRepository) {
    this.magasinRepository = magasinRepository;
    }
    public List<Magasin>getAllMagasins(){
        return magasinRepository.findAll();
    }
    public Optional<Magasin> getMagasinById(int id){
        return magasinRepository.findById(id);
    }
    public Magasin ajoutMagasin(Magasin magasin){
        return magasinRepository.save(magasin);
    }
    public Magasin modifMagasin(Magasin magasin){
        return magasinRepository.save(magasin);
    }
    public void supprimerMagasin(Integer id){
        magasinRepository.deleteById(id);
    }
    public Optional<Magasin> magasinParNom(String nom){
        return magasinRepository.findByNom(nom);
    }
}
