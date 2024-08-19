package com.RNE.referentiel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.RNE.referentiel.entities.Ville;

public interface VilleRepository extends JpaRepository<Ville, String> {

	@Query("SELECT v FROM Ville v WHERE v.activation='Active' ")
	List<Ville> getActivatedVilles();
}
