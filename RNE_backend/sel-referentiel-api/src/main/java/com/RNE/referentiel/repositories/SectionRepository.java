package com.RNE.referentiel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.RNE.referentiel.entities.Section;

public interface SectionRepository extends JpaRepository<Section, String> {

	@Query("SELECT s FROM Section s WHERE s.activation = 'Activate' ")
	List<Section> getActivatedSection();

}
