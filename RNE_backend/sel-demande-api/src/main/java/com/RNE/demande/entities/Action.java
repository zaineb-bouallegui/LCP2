package com.RNE.demande.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Action implements Serializable {

	private static final long serialVersionUID = -6047593005293832737L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "action_en_nature")
	private int actionEnNature;

	@Column(name = "action_en_numeraire")
	private int actionEnNumeraire;

	@Column(name = "valeur_nominale_part")
	private float valeurNominalePart;

	@Column(name = "nbr_Parts")
	private int nbrParts;

	@Column(name = "total_des_actions")
	private float totaldesActions;

	@Column(name = "rapport")
	private float rapport;

	@ManyToOne
	private Societe societe;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "actions")
	private Set<Personne> personnes;

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
