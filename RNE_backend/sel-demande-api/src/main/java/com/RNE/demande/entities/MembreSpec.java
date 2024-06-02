package com.RNE.demande.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.RNE.demande.enums.Genre;
import com.RNE.demande.enums.Pouvoirs;
import com.RNE.demande.enums.Qualite;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MembreSpec implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6974072593311623828L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Qualite qualite;

	@Enumerated(EnumType.STRING)
	private Pouvoirs pouvoirs;

	@Temporal(TemporalType.DATE)
	private Date dateNomination;

	@Temporal(TemporalType.DATE)
	private Date dateFinNomination;

	private Long dureeNomination;
	
	private Boolean isDeposant;
	
	@Enumerated(EnumType.STRING)
	private Genre genre; 
	
	private String adressElec;
	
	private int numTlf;
	
	private String adress;

	@ManyToOne
	private Societe societe;
	

	@ManyToMany
	private List<Personne> personnes;
	
	
}
