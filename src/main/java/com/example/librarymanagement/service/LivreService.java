package com.example.librarymanagement.service;


import com.example.librarymanagement.model.Livre;
import com.example.librarymanagement.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    @Autowired  // injection de dépendence
    public LivreRepository livreRepository;

    public List<Livre> getLivres(){
        return livreRepository.findAll();
    }


    public Livre addNewLivre(Livre livre) {
        return livreRepository.save(livre);
    }
    public void deleteLivreById(Long livreId) {

        boolean exists = livreRepository.existsById(livreId);
        if(!exists){
            throw new IllegalArgumentException( "Le livre avec Id " + livreId + " n'existe pas");
        }

        livreRepository.deleteById(livreId);
    }
    public Livre getLivreById(Long livreId) {
        Optional<Livre> livre = livreRepository.findById(livreId);
        if (livre.isEmpty()) {
            throw new IllegalArgumentException("Le livre avec l'ID " + livreId + " n'existe pas.");
        }
        return livre.get();
    }


}
