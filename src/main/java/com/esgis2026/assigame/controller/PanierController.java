package com.esgis2026.assigame.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esgis2026.assigame.entity.LignePanier;
import com.esgis2026.assigame.entity.Panier;
import com.esgis2026.assigame.service.PanierService;

@RestController
@RequestMapping("/api/panier")
public class PanierController {

    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    // GET /api/panier/{idUtilisateur}
    @GetMapping("/{idUtilisateur}")
    public ResponseEntity<Panier> getPanier(@PathVariable Long idUtilisateur) {
        return ResponseEntity.ok(panierService.getPanierByUtilisateur(idUtilisateur));
    }

    // GET /api/panier/{idUtilisateur}/lignes
    @GetMapping("/{idUtilisateur}/lignes")
    public ResponseEntity<List<LignePanier>> getLignes(@PathVariable Long idUtilisateur) {
        return ResponseEntity.ok(panierService.getLignesByPanier(idUtilisateur));
    }

    // POST /api/panier/{idUtilisateur}/ajouter?idProduit=1&quantite=2
    @PostMapping("/{idUtilisateur}/ajouter")
    public ResponseEntity<Panier> ajouterProduit(
            @PathVariable Long idUtilisateur,
            @RequestParam Long idProduit,
            @RequestParam int quantite) {
        return ResponseEntity.ok(panierService.ajouterProduit(idUtilisateur, idProduit, quantite));
    }

    // PUT /api/panier/ligne/{idLigne}?quantite=3
    @PutMapping("/ligne/{idLigne}")
    public ResponseEntity<LignePanier> updateQuantite(
            @PathVariable Long idLigne,
            @RequestParam int quantite) {
        return ResponseEntity.ok(panierService.updateQuantite(idLigne, quantite));
    }

    // DELETE /api/panier/ligne/{idLigne}
    @DeleteMapping("/ligne/{idLigne}")
    public ResponseEntity<Void> supprimerLigne(@PathVariable Long idLigne) {
        panierService.supprimerLigne(idLigne);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/panier/{idUtilisateur}/vider
    @DeleteMapping("/{idUtilisateur}/vider")
    public ResponseEntity<Void> viderPanier(@PathVariable Long idUtilisateur) {
        panierService.viderPanier(idUtilisateur);
        return ResponseEntity.noContent().build();
    }
}