package com.example.librarymanagement.controller;


import com.example.librarymanagement.model.Livre;
import com.example.librarymanagement.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/livre")
public class LivreController {

    @Autowired // injection de dépendence
    public LivreService livreService ;

    @GetMapping(path= "/getlivres")
    public List<Livre> getLivres() {
        return livreService.getLivres();
    }

    @PostMapping(path = "/postlivre")
    public void registerNewLivre(@RequestBody Livre livre){
        livreService.addNewLivre(livre);
    }

    @DeleteMapping(path = "{livreId}")
    public void deleteLivre(@PathVariable("livreId")Long livreId) {
        livreService.deleteLivreById(livreId);
    }


}
