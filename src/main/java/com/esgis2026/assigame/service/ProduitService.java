package com.esgis2026.assigame.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esgis2026.assigame.entity.Produit;
import com.esgis2026.assigame.entity.Utilisateur;
import com.esgis2026.assigame.entity.CategorieProduit;
import com.esgis2026.assigame.repository.ProduitRepository;
import com.esgis2026.assigame.repository.UtilisateurRepository;
import com.esgis2026.assigame.repository.CategorieProduitRepository;

@Service
public class ProduitService {
    final ProduitRepository produitRepository;
    final UtilisateurRepository utilisateurRepository;
    final CategorieProduitRepository categorieProduitRepository;

    public ProduitService(
            ProduitRepository produitRepository,
            UtilisateurRepository utilisateurRepository,
            CategorieProduitRepository categorieProduitRepository) {
        this.produitRepository = produitRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.categorieProduitRepository = categorieProduitRepository;
    }

    public List<Produit> getAllProduit() {
        return produitRepository.findAll();
    }

    public Produit createProduit(Produit produit) {
        // Résoudre l'utilisateur depuis la BDD (évite l'objet transient)
        if (produit.getUtilisateur() != null && produit.getUtilisateur().getId_utilisateur() != null) {
            Utilisateur u = utilisateurRepository
                    .findById(produit.getUtilisateur().getId_utilisateur())
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
            produit.setUtilisateur(u);
        }

        // Résoudre la catégorie depuis la BDD
        if (produit.getCategorieProduit() != null && produit.getCategorieProduit().getIdcategorie_produit() != null) {
            CategorieProduit cat = categorieProduitRepository
                    .findById(produit.getCategorieProduit().getIdcategorie_produit())
                    .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
            produit.setCategorieProduit(cat);
        }

        if (produit.getDate_ajout() == null) {
            produit.setDate_ajout(java.time.LocalDateTime.now());
        }

        return produitRepository.save(produit);

    }

    public void deleteProduit(Long idProduit) {
        produitRepository.deleteById(idProduit);
    }

    public Produit updateProduit(Long idProduit, Produit details) {
        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new RuntimeException("Produit not found with id " + idProduit));
        produit.setNom_produit(details.getNom_produit());
        produit.setDescription(details.getDescription());
        produit.setPrix(details.getPrix());
        produit.setStatut(details.getStatut());          // utile pour l'admin
        produit.setImage(details.getImage());            // utile si on veut éditer l'image plus tard
        if (details.getCategorieProduit() != null && details.getCategorieProduit().getIdcategorie_produit() != null) {
            CategorieProduit cat = categorieProduitRepository
                    .findById(details.getCategorieProduit().getIdcategorie_produit())
                    .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
            produit.setCategorieProduit(cat);
        }
        return produitRepository.save(produit);
    }
}