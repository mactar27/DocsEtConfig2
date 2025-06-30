package sn.edu.isep.dbe.DocsEtConfig.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sn.edu.isep.dbe.DocsEtConfig.entities.Droit;
import sn.edu.isep.dbe.DocsEtConfig.entities.Role;
import sn.edu.isep.dbe.DocsEtConfig.entities.User;
import sn.edu.isep.dbe.DocsEtConfig.repositories.DroitRepository;
import sn.edu.isep.dbe.DocsEtConfig.repositories.RoleRepository;
import sn.edu.isep.dbe.DocsEtConfig.repositories.UserRepository;

import java.util.List;

@Component
@Order(1)
public class InitUser implements CommandLineRunner {

    private final DroitRepository droitRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public InitUser(DroitRepository droitRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.droitRepository = droitRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Droit creerMagasin=new Droit("creerMag");
        Droit suppMag=new Droit("suppMag");
        Droit creerUser=new Droit("creerUser");
        Droit suppUser=new Droit("suppUser");
        droitRepository.save(creerMagasin);
        droitRepository.save(suppMag);
        droitRepository.save(creerUser);
        droitRepository.save(suppUser);

        Role userRole=new Role("ROLE_NAME");
        Role adminRole=new Role("ROLE_ADMIN");
        Role managerRole=new Role("ROLE_MANAGER");

        roleRepository.save(userRole);
        roleRepository.save(adminRole);
        roleRepository.save(managerRole);

        User abdou= User.builder()
                .prenom("abdou")
                .nom("fall")
                .password("mbaye2005##$$")
                .email("insam621.com@gmail.com")
                .roles(List.of(userRole,adminRole))
                .build();

        User Yacine=User.builder()
                .prenom("Yacine").nom("toure")
                .nom("fall")
                .password("mbaye2005##$$")
                .email("insam.com@gmail.com")
                .roles(List.of(userRole,adminRole))
                .droits(List.of(creerMagasin,suppMag))
                .build();

        User Modou=User.builder()
                .prenom("Lamine")
                .nom("Camara")
                .password("mbaye2005##$$")
                .email("insa.com@gmail.com")
                .roles(List.of(userRole,adminRole))
                .droits(List.of(creerMagasin,suppMag))
                .build();
        userRepository.saveAll(List.of(abdou,Yacine,Modou));

    }

}
