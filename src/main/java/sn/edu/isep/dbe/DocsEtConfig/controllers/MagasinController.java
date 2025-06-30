package sn.edu.isep.dbe.DocsEtConfig.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import sn.edu.isep.dbe.DocsEtConfig.entities.Magasin;
import sn.edu.isep.dbe.DocsEtConfig.entities.User;
import sn.edu.isep.dbe.DocsEtConfig.services.MagasinService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/magasins")
@Tag(name = "Gestion des magasins")
public class MagasinController {

    private final MagasinService magasinService;

    public MagasinController(MagasinService magasinService) {
        this.magasinService = magasinService;
    }
    @GetMapping
    @Operation(
            summary = "Liste magasins",
            description = "Liste de tous les magasins",
            responses = {
                    @ApiResponse(responseCode = "200",description = "renvoie la liste de tous les magasins"),
                    @ApiResponse(responseCode = "500",description = "erreur interne de l'application")

            }

    )
    public List<Magasin> getAllMagasins() {
        System.out.println("&&&&& Lister magasin");
        return magasinService.getAllMagasins();
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Detail d'un magasin",
            description = "recherche un magasin a partir de son identifiant",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "le magasin a ete trouve et retrouve",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Magasin.class),
                                    examples = {@ExampleObject("" +
                                            "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nom\": \"Dfe tech Shop\",\n" +
                                            "  \"adresse\": \"Diamniadio\",\n" +
                                            "  \"description\": null,\n" +
                                            "  \"telephone\": \"788003583\"\n" +
                                            "}")}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "y'a pas de magasin"
                    )
    }
    )
    public ResponseEntity getMagasinById(
            @Parameter(description = "identifiant du magasin a rechercher")
            @PathVariable Integer id) {
        Optional<Magasin> magasin = magasinService.getMagasinById(id);
        if (magasin.isPresent()) {
            return ResponseEntity.ok().body(magasin.get());
        }else{

        return ResponseEntity.notFound().build();
    }
        }
        @PostMapping
    public ResponseEntity ajoutNouvelMagasin(@RequestBody Magasin magasin, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        String login=(String) authentication.getCredentials();
        Collection<? extends GrantedAuthority> droits= authentication.getAuthorities();
            System.out.println("Enregistrement de nouvel magasin");
            System.out.println("login : "+login);
            System.out.println("les droits sont" +droits);
            System.out.println("le user est"+user);
            System.out.println("le magasin est"+magasin);
            magasin.setCreateur(user);
        if (magasin.getId()!=null){
            return ResponseEntity.status(600).body("l'id est obligatoire");

        }
        if (magasin.getNom()==null){
            return ResponseEntity.status(601).body("le nom est obligatoire");
        }
        if (magasin.getAdresse()==null){
            return ResponseEntity.status(602).body("l'addresse est obligatoire");
        }
        Optional<Magasin> tmp=magasinService.magasinParNom(magasin.getNom());
        if (tmp.isPresent()){
            return ResponseEntity.status(603).body("un magasin avec le nom existe deja"+magasin.getNom()+"'existe deja");
        }
        magasinService.ajoutMagasin(magasin);
        return ResponseEntity.status(201).body(magasin);
//        return null;
        }
    @PutMapping("/{id}")
    @Operation(
            summary = "Modifier un magasin",
            description = "Modifie les informations d'un magasin existant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Magasin modifié avec succès"),
                    @ApiResponse(responseCode = "404", description = "Magasin non trouvé"),
                    @ApiResponse(responseCode = "400", description = "Données invalides")
            }
    )
    public ResponseEntity<?> modifierMagasin(
            @PathVariable Integer id,
            @RequestBody Magasin magasin) {
        Optional<Magasin> existant = magasinService.getMagasinById(id);
        if (existant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (magasin.getNom() == null) {
            return ResponseEntity.badRequest().body("le nom est obligatoire");
        }
        if (magasin.getAdresse() == null) {
            return ResponseEntity.badRequest().body("l'adresse est obligatoire");
        }
        Magasin magasinToUpdate = existant.get();
        magasinToUpdate.setNom(magasin.getNom());
        magasinToUpdate.setAdresse(magasin.getAdresse());
//        magasinToUpdate.setDescription(magasin.getDescription());
        magasinToUpdate.setTelephone(magasin.getTelephone());
        magasinService.ajoutMagasin(magasinToUpdate);
        return ResponseEntity.ok(magasinToUpdate);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer un magasin",
            description = "Supprime un magasin à partir de son identifiant",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Magasin supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Magasin non trouvé")
            }
    )
    public ResponseEntity<Void> supprimerMagasin(@PathVariable Integer id) {
        Optional<Magasin> magasin = magasinService.getMagasinById(id);
        if (magasin.isPresent()) {
            magasinService.supprimerMagasin(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}