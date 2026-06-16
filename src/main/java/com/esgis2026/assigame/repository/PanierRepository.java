package com.esgis2026.assigame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgis2026.assigame.entity.Panier;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
    Optional<Panier> findByUtilisateur_Id_utilisateur(Long idUtilisateur);
}