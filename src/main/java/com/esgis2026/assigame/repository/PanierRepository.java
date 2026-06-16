package com.esgis2026.assigame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esgis2026.assigame.entity.Panier;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
    @Query("SELECT p FROM Panier p WHERE p.utilisateur.id_utilisateur = :idUtilisateur")
    Optional<Panier> findByUtilisateur(@Param("idUtilisateur") Long idUtilisateur);
}