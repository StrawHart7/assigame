package com.esgis2026.assigame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lignecommande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lignecommande;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_commande", nullable = false)
    private Commande commande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit", nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private int quantite;

    @Column(nullable = false)
    private double prix_unitaire;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_lignecommande == null) ? 0 : id_lignecommande.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        LigneCommande other = (LigneCommande) obj;
        if (id_lignecommande == null) {
            if (other.id_lignecommande != null) return false;
        } else if (!id_lignecommande.equals(other.id_lignecommande)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LigneCommande [id_lignecommande=" + id_lignecommande + ", quantite=" + quantite
                + ", prix_unitaire=" + prix_unitaire + "]";
    }
}