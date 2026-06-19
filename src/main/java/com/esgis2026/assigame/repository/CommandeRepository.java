package com.esgis2026.assigame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esgis2026.assigame.entity.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    @Query("SELECT c FROM Commande c WHERE c.utilisateur.id_utilisateur = :idUtilisateur")
    List<Commande> findByUtilisateur(@Param("idUtilisateur") Long idUtilisateur);
}