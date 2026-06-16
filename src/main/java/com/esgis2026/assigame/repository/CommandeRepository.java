package com.esgis2026.assigame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgis2026.assigame.entity.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByUtilisateur_Id_utilisateur(Long idUtilisateur);
}