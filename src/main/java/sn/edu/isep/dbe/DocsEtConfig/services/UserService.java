package sn.edu.isep.dbe.DocsEtConfig.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sn.edu.isep.dbe.DocsEtConfig.entities.Role;
import sn.edu.isep.dbe.DocsEtConfig.entities.User;
import sn.edu.isep.dbe.DocsEtConfig.entities.UserToken;
import sn.edu.isep.dbe.DocsEtConfig.entities.dto.LoginResponse;
import sn.edu.isep.dbe.DocsEtConfig.repositories.UserRepository;
import sn.edu.isep.dbe.DocsEtConfig.repositories.UserTokenRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    @Value("${magasin.auth.token.length}")
    private int tokenSize;
    @Value("${magasin.auth.token.validite}")
    private int validite;
    @Value("${magasin.auth.token.caracteres}")
    private String caracteres;


    public UserService (UserRepository userRepository, UserTokenRepository userTokenRepository) {

        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }
    public User ajoutUser(User user){
        return userRepository.save(user);
    }
    public User modifierUser(User user){
        return userRepository.save(user);
    }
    public void supprimerUser(Integer id){
        userRepository.deleteById(id);
    }
    public Optional<User> findByNom(String nom){
        return Optional.ofNullable(userRepository.findByNom(nom));
    }
    public Optional<User> getUserfindByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public LoginResponse login(String email, String password){
        Optional<User> userData = userRepository.findByEmail(email);
        if(userData.isPresent()){
            User user = userData.get();
            if(user.getPassword().equals(password)){
                // List<String> roles=user.getRoles().stream().map(role -> role.getNom()).collect(Collectors.toList());
                //creation de role
                List<String> roles = new ArrayList<>();
                //creation de droit
                List<String> droits = new ArrayList<>();
                //parcours des roles

                for(Role role : user.getRoles()){
                    String nomrole = role.getNom();
                    if (nomrole.startsWith("ROLE_ADMIN")){
                        roles.add(nomrole);
                    }else{
                        droits.add(nomrole);
                    }
                    roles.add(role.getNom());
                }
                UserToken userToken =generateUserToken(user);


                return   LoginResponse.builder()
                        .email(user.getEmail())
                        .nom(user.getNom())
                        .prenom(user.getPrenom())
                        .token(userToken.getToken())
                        .droits(droits)
                        .roles(roles)
                        .dateExpiration(userToken.getExpiresAt())
                        .notBefore(userToken.getExpiresAt())
                        .build();
            }
        }
        return null;
    }
    public UserToken generateUserToken(User user){
        while (true){
            String token = generateToken();

            Optional<UserToken> userTokenData = userTokenRepository.findById(token);

            if(userTokenData.isEmpty()){
                UserToken userToken = new UserToken();
                Date nowDay = new Date();

                long nbMilliSeconds=validite*60*60*1000;

                Date expirationDAte = new Date(nowDay.getTime() + validite*1000*60*60);
                userToken.setToken(token);
                userToken.setUser(user);
                userToken.setExpiresAt(expirationDAte);
                userToken.setNoteBefore(nowDay);

                userTokenRepository.save(userToken);

                return userToken;
            }
        }

    }
    public String generateToken(){
        StringBuilder tokenBuilder = new StringBuilder();
        for (int i = 0; i < tokenSize; i++){
            int randomIndex = (int) (Math.random() *caracteres.length());
            tokenBuilder.append(caracteres.charAt(randomIndex));
        }
        return tokenBuilder.toString();
    }
}