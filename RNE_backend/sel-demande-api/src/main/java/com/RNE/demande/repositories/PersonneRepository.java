package com.RNE.demande.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RNE.demande.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
