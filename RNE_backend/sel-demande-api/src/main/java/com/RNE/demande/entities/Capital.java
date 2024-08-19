package com.RNE.demande.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
public class Capital implements Serializable {

	private static final long serialVersionUID = -1044033486519914823L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nb_associes")
	private int nbAssocies;

	@Column(name = "capital_TDN")
	private Float capitalTDN;

	@Column(name = "capitalMin")
	private Float capitalMin;

	@Column(name = "action")
	private Float action;

	@Column(name = "nbr_parts")
	private int nbrParts;

	@Column(name = "valeur_parts_nature")
	private int valeurPartsNat;

	@Column(name = "valeur_parts_numeraire")
	private int valeurPartsNum;

	@Column(name = "date_cloture")
	@Temporal(TemporalType.DATE)
	private Date dateCloture;

	private int dureeSociete;

	@OneToOne
	private Societe societe;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		createdAt = now;
		updatedAt = now;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
}
