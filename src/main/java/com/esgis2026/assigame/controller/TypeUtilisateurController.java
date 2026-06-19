package com.esgis2026.assigame.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esgis2026.assigame.entity.TypeUtilisateur;
import com.esgis2026.assigame.service.TypeUtilisateurService;

@RestController
@RequestMapping("/api/typeutilisateur")
public class TypeUtilisateurController {
  
    private final TypeUtilisateurService typeUtilisateurService;

    public TypeUtilisateurController(TypeUtilisateurService typeUtilisateurService) {
        this.typeUtilisateurService = typeUtilisateurService;
    }

    @GetMapping("/list")
    public List<TypeUtilisateur> getAllTypeUtilisateur() {
        return typeUtilisateurService.getAllTypeUtilisateur();
    }

    @PostMapping("/add")
    public TypeUtilisateur addTypeUtilisateur(
            @RequestBody TypeUtilisateur typeUtilisateur) {

        return typeUtilisateurService.createTypeUtilisateur(typeUtilisateur);
    }
    
     @PutMapping("/update/{id}")
    public ResponseEntity<TypeUtilisateur> updateTypeUtilisateur(
            @PathVariable Long id,
            @RequestBody TypeUtilisateur typeUtilisateur) {
        return ResponseEntity.ok(typeUtilisateurService.updateTypeUtilisateur(id, typeUtilisateur));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTypeUtilisateur(@PathVariable Long id) {
        typeUtilisateurService.deleteTypeUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
 
    

}
