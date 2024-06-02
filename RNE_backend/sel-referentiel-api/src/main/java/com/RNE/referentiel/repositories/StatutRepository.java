package com.RNE.referentiel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RNE.referentiel.entities.Statut;

public interface StatutRepository extends JpaRepository<Statut, String> {

}
