package com.RNE.referentiel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.RNE.referentiel.entities.Gouvernorat;

@Repository
public interface GouvernoratRepository extends JpaRepository<Gouvernorat, String> {

	@Query("SELECT g FROM Gouvernorat g WHERE g.activation = 'Active' ")
	List<Gouvernorat> getActivatedGouvernerats();
}
