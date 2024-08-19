package com.RNE.demande.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.RNE.demande.enums.AdresseType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Adresse implements Serializable {

	private static final long serialVersionUID = -6047593005293832737L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gov_code", nullable = false)
	private String govCode;

	@Column(name = "code_Ville", nullable = false)
	private String codeVille;

	@Column(name = "code_Postal", nullable = false)
	private String codePostal;

	@Column(name = "rue_fr", nullable = false)
	private String rueFr;

	@Column(name = "rue_Ar", nullable = false)
	private String rueAr;

	@ManyToOne
	private Societe societe;

	@OneToOne(mappedBy = "adresse")
	private Personne personne;

	@Enumerated(EnumType.STRING)
	@Column(name = "adresse_type", nullable = false)
	private AdresseType adresseType;

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
