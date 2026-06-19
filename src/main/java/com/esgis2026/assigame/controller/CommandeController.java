package com.esgis2026.assigame.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esgis2026.assigame.entity.Commande;
import com.esgis2026.assigame.service.CommandeService;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    // POST /api/commande/valider/{idUtilisateur}
    @PostMapping("/valider/{idUtilisateur}")
    public ResponseEntity<Commande> validerPanier(@PathVariable Long idUtilisateur) {
        return ResponseEntity.ok(commandeService.validerPanier(idUtilisateur));
    }

    // GET /api/commande/utilisateur/{idUtilisateur}
    @GetMapping("/utilisateur/{idUtilisateur}")
    public ResponseEntity<List<Commande>> getCommandesUtilisateur(@PathVariable Long idUtilisateur) {
        return ResponseEntity.ok(commandeService.getCommandesByUtilisateur(idUtilisateur));
    }

    // GET /api/commande/list
    @GetMapping("/list")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }
}