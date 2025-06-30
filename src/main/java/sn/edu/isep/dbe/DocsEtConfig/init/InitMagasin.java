package sn.edu.isep.dbe.DocsEtConfig.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sn.edu.isep.dbe.DocsEtConfig.entities.Magasin;
import sn.edu.isep.dbe.DocsEtConfig.entities.User;
import sn.edu.isep.dbe.DocsEtConfig.repositories.MagasinRepository;
import sn.edu.isep.dbe.DocsEtConfig.repositories.UserRepository;

@Component
@Order(2)
@Profile({"dev","test"})
public class InitMagasin implements CommandLineRunner {

    private final MagasinRepository magasinRepository;
    private final UserRepository userRepository;

    @Value("${magasin.dfe.nom}")
    private String nomMagasin;

    @Value("${magasin.dfe.tel}")
    private String telMagasin;

    @Value("${magasin.dfe.adresse}")
    private String adresseMagasin;

    @Value("${magasin.dfe.nombre}")
    private Integer nombreMagasin;

    public InitMagasin(MagasinRepository magasinRepository, UserRepository userRepository) {
        this.magasinRepository = magasinRepository;
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("Initialisation des donnees");
        Magasin m=new Magasin();
        m.setNom(nomMagasin);
        m.setAdresse(adresseMagasin);
        m.setTelephone(telMagasin);
        User user=userRepository.findById(1).get();
        m.setCreateur(user);

        magasinRepository.save(m);
        for(int i = 0; i < nombreMagasin; i++){
            Magasin m2=new Magasin();
            m2.setNom("magasin" +i);
            m2.setAdresse("adresse" +i);
            m2.setTelephone("tel" +i);
            m2.setCreateur(user);

            magasinRepository.save(m2);
        }

    }
}
