package com.esgis2026.assigame.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_commande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private LocalDateTime date_commande;

    @Column(nullable = false, length = 20)
    private String statut;

    @Column(nullable = false)
    private double montant_total;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommande> lignes = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.date_commande == null) {
            this.date_commande = LocalDateTime.now();
        }
        if (this.statut == null) {
            this.statut = "EN_ATTENTE";
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_commande == null) ? 0 : id_commande.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Commande other = (Commande) obj;
        if (id_commande == null) {
            if (other.id_commande != null) return false;
        } else if (!id_commande.equals(other.id_commande)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Commande [id_commande=" + id_commande + ", date_commande=" + date_commande
                + ", statut=" + statut + ", montant_total=" + montant_total + "]";
    }
}