package com.RNE.demande.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RNE.demande.entities.Demande;

public interface DemandeRepository extends JpaRepository<Demande, Long>{

}
