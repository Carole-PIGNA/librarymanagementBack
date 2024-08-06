package com.example.librarymanagement.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data  // pour les getters et les setters
@AllArgsConstructor  // constructeur avec tous les attributs
@NoArgsConstructor  // constructeur sans attributs
@Entity(name = "livre")//si je ne déclare pas comme entity je n'aurai pas de persistance de donnée
// j'ai rajouté le name aussi pour Hibernate
public class Livre {


    /* Pourquoi je déclare tout en private? -> contrôle de la modif des atributs depuis l'extérieur;
     ->  Principe d'encapsulation: regroupement des données(attributs) et méthodes qui manipulent ces données dans la même classe.
     En mettant les attributs privées, j'oblige les méthodes publiques à passer par les getters et les setters
     */
    @Id  // ça veut dire que le champ après est une clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // la valeur de la clé primaire sera générée automatiquement par auto-incrémentation
    private int id;
    private String titre;
    private String auteur;
    private Date date_edition;

    @Enumerated(EnumType.STRING) // Spécifie que l'énum doit être stocké en tant que chaîne
    private Genre genre;

    //constructeur sans id
    public Livre (String titre, String auteur, Date date_edition, Genre genre){
        this.titre = titre;
        this.auteur = auteur;
        this.date_edition = date_edition;
        this.genre = genre;

    }


}
