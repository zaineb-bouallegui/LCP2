package com.RNE.demande.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.RNE.demande.enums.Genre;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Personne implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6047593005293832737L;
	/**
	* 
	*/
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "ID_Carte")
	private Long IDCarte;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_Delivrance")
	private Date date_Delivrance;


	@Temporal(TemporalType.DATE)
	@Column(name = "date_Naiss")
	private Date date_Naiss;

	@Column(name = "lieu_naiss_ar")
	private String lieu_naiss_ar;
	
	
	@Column(name = "lieu_naiss_fr")
	private String lieu_naiss_fr;
	
	
	@Column(name = "nom_prenom_ar")
	private String nom_prenom_ar;
	
	@Column(name = "nom_prenom_fr")
	private String nom_prenom_fr;

	

	@Enumerated(EnumType.STRING)
	@Column(name = "genre")
	private Genre genre;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Action> actions;
	
	
	@ManyToMany(mappedBy = "personnes")

	private List<MembreSpec> membreSpec;
	

	@OneToOne
	private Adresse adresse;

}
