package com.esgis2026.assigame.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esgis2026.assigame.entity.LignePanier;
import com.esgis2026.assigame.entity.Panier;
import com.esgis2026.assigame.entity.Produit;
import com.esgis2026.assigame.entity.Utilisateur;
import com.esgis2026.assigame.repository.LignePanierRepository;
import com.esgis2026.assigame.repository.PanierRepository;
import com.esgis2026.assigame.repository.ProduitRepository;
import com.esgis2026.assigame.repository.UtilisateurRepository;

@Service
public class PanierService {

    final PanierRepository panierRepository;
    final LignePanierRepository lignePanierRepository;
    final ProduitRepository produitRepository;
    final UtilisateurRepository utilisateurRepository;

    public PanierService(PanierRepository panierRepository,
            LignePanierRepository lignePanierRepository,
            ProduitRepository produitRepository,
            UtilisateurRepository utilisateurRepository) {
        this.panierRepository = panierRepository;
        this.lignePanierRepository = lignePanierRepository;
        this.produitRepository = produitRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Panier getPanierByUtilisateur(Long idUtilisateur) {
        return panierRepository.findByUtilisateur(idUtilisateur)
                .orElseGet(() -> {
                    Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                            .orElseThrow(() -> new RuntimeException("Utilisateur not found with id " + idUtilisateur));
                    Panier panier = new Panier();
                    panier.setUtilisateur(utilisateur);
                    return panierRepository.save(panier);
                });
    }

    public Panier ajouterProduit(Long idUtilisateur, Long idProduit, int quantite) {
        Panier panier = getPanierByUtilisateur(idUtilisateur);

        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new RuntimeException("Produit not found with id " + idProduit));

        for (LignePanier ligne : panier.getLignes()) {
            if (ligne.getProduit().getId_produit().equals(idProduit)) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                lignePanierRepository.save(ligne);
                return panierRepository.save(panier);
            }
        }

        LignePanier ligne = new LignePanier();
        ligne.setPanier(panier);
        ligne.setProduit(produit);
        ligne.setQuantite(quantite);
        panier.getLignes().add(ligne);

        return panierRepository.save(panier);
    }

    public LignePanier updateQuantite(Long idLigne, int quantite) {
        LignePanier ligne = lignePanierRepository.findById(idLigne)
                .orElseThrow(() -> new RuntimeException("LignePanier not found with id " + idLigne));
        ligne.setQuantite(quantite);
        return lignePanierRepository.save(ligne);
    }

    public void supprimerLigne(Long idLigne) {
        lignePanierRepository.deleteById(idLigne);
    }

    public void viderPanier(Long idUtilisateur) {
        Panier panier = getPanierByUtilisateur(idUtilisateur);
        panier.getLignes().clear();
        panierRepository.save(panier);
    }

    public List<LignePanier> getLignesByPanier(Long idUtilisateur) {
        Panier panier = getPanierByUtilisateur(idUtilisateur);
        return panier.getLignes();
    }
}