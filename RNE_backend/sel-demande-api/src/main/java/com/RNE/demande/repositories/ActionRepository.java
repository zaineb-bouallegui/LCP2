package com.RNE.demande.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RNE.demande.entities.Action;

public interface ActionRepository extends JpaRepository<Action, Long> {

}
