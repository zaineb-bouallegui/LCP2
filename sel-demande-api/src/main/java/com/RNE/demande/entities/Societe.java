package com.RNE.demande.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import com.RNE.demande.enums.OrigineFondCommercial;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Societe implements Serializable {

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

	@Column(name = "denomination_Sociale_Fr")
	private String denomination_Sociale_Fr;

	@Column(name = "denomination_Sociale_Ar")
	private String denomination_Sociale_Ar;

	@Column(name = "email")
	private String email;


	@Column(name = "n_reservation")
	private String num_Reservation;

	@Column(name = "cNom_Commercial")
	private Boolean cNom_Commercial;

	@Column(name = "cEnseigne")
	private Boolean cEnseigne;

	@Column(name = "nbEmployes")
	private int nbEmployes;

	@Enumerated(EnumType.STRING)
	@Column(name = "origine_Fond_Commercial")
	private OrigineFondCommercial origine_Fond_Commercial;

	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Activite> activities;

	@OneToOne(mappedBy = "societe")
	@JsonIgnore
	private Capital capital;

	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Demande> demandes;

	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Action> actions;

	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<MembreSpec> membreSpec;

	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Adresse> adresses;
	
	@ManyToOne
	@JsonIgnore
	private FormeJuridique formeJuridique;
	
	private String numBenificiaire;
}