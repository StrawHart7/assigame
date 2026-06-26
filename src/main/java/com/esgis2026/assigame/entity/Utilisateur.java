package com.esgis2026.assigame.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_utilisateur;

    @JsonProperty("Nom")
    @Column(nullable = false, length = 50)
    private String Nom;

    @JsonProperty("Prenom")
    @Column(nullable = false, length = 50)
    private String Prenom;

    @JsonProperty("Email")
    @Column(unique = true, nullable = false, length = 100)
    private String Email;

    @JsonProperty("Motdepasse")
    @Column(nullable = false, length = 100)
    private String Motdepasse;

    @JsonProperty("Login")
    @Column(nullable = false, unique = true, length = 10)
    private String Login;

    @Column(nullable = true, length = 20)
    private String telephone;

    @Column(nullable = false, length = 20)
    private String statut;

    // USER par défaut, ADMIN pour les administrateurs
    @Column(nullable = true, length = 20)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_typeutilisateur")
    private TypeUtilisateur typeutilisateur;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_utilisateur == null) ? 0 : id_utilisateur.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Utilisateur other = (Utilisateur) obj;
        if (id_utilisateur == null) {
            if (other.id_utilisateur != null) return false;
        } else if (!id_utilisateur.equals(other.id_utilisateur)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur [id_utilisateur=" + id_utilisateur + ", Nom=" + Nom + ", Prenom=" + Prenom
                + ", Email=" + Email + ", Login=" + Login
                + ", telephone=" + telephone + ", statut=" + statut + ", role=" + role + "]";
    }
}