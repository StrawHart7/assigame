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
@Table(name = "produit")

public class Produit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produit;
    
    @Column(unique = false, nullable = false, length = 50)
    private String nom_produit;
    
    @Column(unique = false, nullable =true, length = 200 )
    private String description;
    
    @Column(unique = false, nullable = true)
    private double prix; 
    
    @Column()
    private String image;
    
    @Column(unique = false, nullable = false)
    private LocalDateTime date_ajout; 
    
    
    @Column(unique = false, nullable = false)
    private String statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcategorie_produit")
    private CategorieProduit categorieProduit;
     



    
    

    




}
