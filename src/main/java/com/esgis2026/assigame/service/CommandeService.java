package com.esgis2026.assigame.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esgis2026.assigame.entity.Commande;
import com.esgis2026.assigame.entity.LigneCommande;
import com.esgis2026.assigame.entity.LignePanier;
import com.esgis2026.assigame.entity.Panier;
import com.esgis2026.assigame.entity.Utilisateur;
import com.esgis2026.assigame.repository.CommandeRepository;
import com.esgis2026.assigame.repository.UtilisateurRepository;

@Service
public class CommandeService {

    final CommandeRepository commandeRepository;
    final PanierService panierService;
    final UtilisateurRepository utilisateurRepository;

    public CommandeService(CommandeRepository commandeRepository,
            PanierService panierService,
            UtilisateurRepository utilisateurRepository) {
        this.commandeRepository = commandeRepository;
        this.panierService = panierService;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Commande validerPanier(Long idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found with id " + idUtilisateur));

        Panier panier = panierService.getPanierByUtilisateur(idUtilisateur);

        if (panier.getLignes().isEmpty()) {
            throw new RuntimeException("Le panier est vide");
        }

        Commande commande = new Commande();
        commande.setUtilisateur(utilisateur);

        double montantTotal = 0;

        for (LignePanier lp : panier.getLignes()) {
            LigneCommande lc = new LigneCommande();
            lc.setCommande(commande);
            lc.setProduit(lp.getProduit());
            lc.setQuantite(lp.getQuantite());
            lc.setPrix_unitaire(lp.getProduit().getPrix());
            commande.getLignes().add(lc);
            montantTotal += lp.getProduit().getPrix() * lp.getQuantite();
        }

        commande.setMontant_total(montantTotal);
        Commande saved = commandeRepository.save(commande);

        panierService.viderPanier(idUtilisateur);

        return saved;
    }

    public List<Commande> getCommandesByUtilisateur(Long idUtilisateur) {
        return commandeRepository.findByUtilisateur(idUtilisateur);
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }
}