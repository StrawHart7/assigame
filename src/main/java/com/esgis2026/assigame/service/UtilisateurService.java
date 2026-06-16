package com.esgis2026.assigame.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esgis2026.assigame.entity.Utilisateur;
import com.esgis2026.assigame.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long idUtilisateur) {
        utilisateurRepository.deleteById(idUtilisateur);
    }

    public Utilisateur updateUtilisateur(Long idUtilisateur, Utilisateur details) {

        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur not found with id "+ idUtilisateur));

     utilisateur.setNom(details.getNom());
     utilisateur.setPrenom(details.getPrenom());
     utilisateur.setEmail(details.getEmail());
     utilisateur.setMotdepasse(details.getMotdepasse());
      
     return utilisateurRepository.save(utilisateur);
    }                 

       


       
}
