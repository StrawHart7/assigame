package com.esgis2026.assigame.service;

import org.springframework.stereotype.Service;

import com.esgis2026.assigame.entity.Utilisateur;
import com.esgis2026.assigame.repository.UtilisateurRepository;

@Service
public class AuthService {

    final UtilisateurRepository utilisateurRepository;

    public AuthService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur login(String email, String motdepasse) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Aucun compte trouvé avec cet email"));

        if (!utilisateur.getMotdepasse().equals(motdepasse)) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return utilisateur;
    }

    public Utilisateur register(Utilisateur utilisateur) {
        utilisateurRepository.findByEmail(utilisateur.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Un compte existe déjà avec cet email");
                });
        return utilisateurRepository.save(utilisateur);
    }
}