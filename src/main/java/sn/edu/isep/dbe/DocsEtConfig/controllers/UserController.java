package sn.edu.isep.dbe.DocsEtConfig.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name="Gestion des utilisateurs",description = "c'est une application qui gère les utilisateurs !")
public class UserController {
    @Operation(summary = "la liste des utilisateurs !")
    @GetMapping
    public void test(){

    }
    @Operation(summary = "Ajouter un utilisateur")
    @PostMapping
    public void test1(){

    }
    //les tags nous permettent d'annoter le document en précisant que la partie est un autre dossier à part qui joue un autre rôle !
    //s'il y a un seul tag ,le controleur va conserner tous les méthodes !
    @Tag(name="l'autre tag !")
    @Operation(summary = "Supprimer un utilisateur")
    @DeleteMapping
    public void test10(){
    }

}
