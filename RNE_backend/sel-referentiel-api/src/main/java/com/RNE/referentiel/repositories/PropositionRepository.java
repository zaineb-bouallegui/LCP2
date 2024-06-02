package com.RNE.referentiel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RNE.referentiel.entities.Proposition;

public interface PropositionRepository extends JpaRepository<Proposition, String> {

}
