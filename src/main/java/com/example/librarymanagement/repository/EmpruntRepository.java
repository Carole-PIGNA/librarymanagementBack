package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  EmpruntRepository extends JpaRepository<Emprunt,Long> {
}
