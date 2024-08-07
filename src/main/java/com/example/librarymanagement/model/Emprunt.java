package com.example.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // pour les getters et les setters
@AllArgsConstructor// constructeur avec tous les attributs
@NoArgsConstructor
@Entity
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    //plusieurs emprunts peuvent être lié à un livre
    @ManyToOne
    @JoinColumn(name = "livre_id", nullable = false)
    @JsonBackReference //important sinon j'aurai mon api en boucle ( faire aussi @JsonManagedReference dans l'entité Livre
    private Livre livre;

    private String emprunteur;

    public Emprunt (Livre livre, String emprunteur){
        this.livre = livre;
        this.emprunteur = emprunteur;
    }

}
