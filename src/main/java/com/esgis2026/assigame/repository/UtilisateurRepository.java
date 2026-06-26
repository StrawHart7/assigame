package com.esgis2026.assigame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esgis2026.assigame.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Tous les champs sont en majuscule dans l'entité (Email, Login)
    // PartTree ne gère pas ça → @Query sur tout

    @Query("SELECT u FROM Utilisateur u WHERE u.Email = :email")
    Optional<Utilisateur> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Utilisateur u WHERE u.Login = :identifiant OR u.Email = :identifiant")
    Optional<Utilisateur> findByLoginOrEmail(@Param("identifiant") String identifiant);

}