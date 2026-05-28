package com.esgis2026.assigame.entity;

import java.time.LocalDateTime;



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
@Table(name = "utilisateur")

public class Utilisateur {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id_utilisateur;

   @Column(nullable = false, length = 50)
   private String nom;

   @Column(nullable = false, length = 50)
   private String prenom;

   @Column(unique = true, nullable = false, length = 100)
   private String email; 

   @Column(nullable = false, length = 100)
   private String motdepasse;

   @Column(nullable = true, length = 20)
   private String telephone;

   @Column(nullable = false)
   private LocalDateTime date_creation;

   @Column(nullable = false, length = 20)
   private String statut;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_typeutilisateur")
   private TypeUtilisateur typeutilisateur;
   

}
