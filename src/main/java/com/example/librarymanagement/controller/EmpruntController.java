package com.example.librarymanagement.controller;


import com.example.librarymanagement.model.Emprunt;
import com.example.librarymanagement.model.Livre;
import com.example.librarymanagement.service.EmpruntService;
import com.example.librarymanagement.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/emprunt")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private LivreService livreService;

    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return empruntService.getAllEmprunts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Long id) {
        Emprunt emprunt = empruntService.getEmpruntById(id);
        if (emprunt != null) {
            return ResponseEntity.ok(emprunt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Emprunt> createEmprunt(@RequestParam Long livreId, @RequestParam String emprunteur) {
        Livre livre = livreService.getLivreById(livreId);
        if (livre != null) {
            Emprunt emprunt = new Emprunt(livre, emprunteur);
            empruntService.saveEmprunt(emprunt);
            return ResponseEntity.ok(emprunt);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        if (empruntService.deleteEmpruntById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
