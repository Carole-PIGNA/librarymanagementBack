package com.example.librarymanagement.controller;


import com.example.librarymanagement.model.Genre;
import com.example.librarymanagement.model.Livre;
import com.example.librarymanagement.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(path = "/api/v1/livre")

public class LivreController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired // injection de dépendence
    public LivreService livreService ;

    @GetMapping(path= "/getlivres")
    public List<Livre> getLivres() {
        return livreService.getLivres();
    }

    @PostMapping(path = "/postlivre")
    public ResponseEntity<Livre> registerNewLivre(@RequestParam("title") String title,
                                                  @RequestParam("author") String author,
                                                  @RequestParam("pub_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date pub_date,
                                                  @RequestParam("genre") String genre,
                                                  @RequestParam("image") MultipartFile image) throws IOException {

        // Convertir le genre à partir de la chaîne (si nécessaire)
        Genre bookGenre = Genre.valueOf(genre.toUpperCase());

        // Stocker l'image
        String imagePath = saveImage(image);

        Livre livre = new Livre(title, author, pub_date, bookGenre, imagePath);

        livreService.addNewLivre(livre);

        return ResponseEntity.ok(livre);
    }

    private String saveImage(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            return null;
        }

        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "-" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadPath, fileName);
        Files.write(filePath, image.getBytes());

        return fileName;

    }


        @DeleteMapping(path = "{livreId}")
        public void deleteLivre (@PathVariable("livreId") Long livreId){
            livreService.deleteLivreById(livreId);
        }

        @GetMapping(path= "/getlivres/{livreId}")
        public Livre getLivreById(@PathVariable("livreId") Long livreId) {

            return livreService.getLivreById(livreId);
        }


    }
