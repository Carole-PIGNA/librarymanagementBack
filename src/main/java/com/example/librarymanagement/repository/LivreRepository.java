package com.example.librarymanagement.repository;


import com.example.librarymanagement.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

// je fais étendre l'interface JpaRepo qui va implémenter automatiquement les opérations CRUD pour Livre (j'aurai save, findById, findAll, deleteById...)
public interface LivreRepository extends JpaRepository<Livre,Long> {

}
