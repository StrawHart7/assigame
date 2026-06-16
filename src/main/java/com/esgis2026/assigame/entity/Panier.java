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
@Table(name = "panier")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_panier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private LocalDateTime date_creation;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignePanier> lignes = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.date_creation == null) {
            this.date_creation = LocalDateTime.now();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_panier == null) ? 0 : id_panier.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Panier other = (Panier) obj;
        if (id_panier == null) {
            if (other.id_panier != null) return false;
        } else if (!id_panier.equals(other.id_panier)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Panier [id_panier=" + id_panier + ", date_creation=" + date_creation + "]";
    }
}