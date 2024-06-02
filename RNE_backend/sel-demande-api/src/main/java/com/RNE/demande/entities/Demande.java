package com.RNE.demande.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.RNE.demande.enums.StatutDemande;
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
public class Demande implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6047593005293832737L;
	/**
	* 
	*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "deadline")
	@Temporal(TemporalType.DATE)
	private Date deadline;

	@Column(name = "date_Envoi")
	@Temporal(TemporalType.DATE)
	private Date dateEnvoi;

	@Column(name = "type_Registre")
	private String typeRegistre;

	@Enumerated(EnumType.STRING)
	@Column(name = "statut_Demande")
	private StatutDemande statutDemande;
	
	@ManyToOne
	private Societe societe;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "demande")
	
	@JsonIgnore
	private Set<Documents> documents;
	
	private String codeStatut;
	
	private String TypePaiment;

	

}