package com.RNE.demande.entities;

import java.io.Serializable;
import java.util.Date;

import com.RNE.demande.enums.ActiviteLocal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Activite implements Serializable {
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

	@Column(name = "nature_activiteP_fr")
	private String natureActivitePFr;

	@Column(name = "nature_activiteP_ar")
	private String natureActivitePAr;

	@Column(name = "nature_activiteS_fr")
	private String natureActiviteSFr;

	@Column(name = "nature_activiteS_ar")
	private String natureActiviteSAr;

	@Enumerated(EnumType.STRING)
	@Column(name = "activite_local")
	private ActiviteLocal activiteLocal;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut_activite")
	private Date dateDebutActivite;

	@ManyToOne
	private Societe societe;
}
