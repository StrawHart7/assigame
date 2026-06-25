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
@Table(name = "lignepanier")
public class LignePanier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lignepanier;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_panier", nullable = false)
    private Panier panier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit", nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private int quantite;

    private Double prix_unitaire;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_lignepanier == null) ? 0 : id_lignepanier.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        LignePanier other = (LignePanier) obj;
        if (id_lignepanier == null) {
            if (other.id_lignepanier != null) return false;
        } else if (!id_lignepanier.equals(other.id_lignepanier)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LignePanier [id_lignepanier=" + id_lignepanier + ", quantite=" + quantite + "]";
    }
}