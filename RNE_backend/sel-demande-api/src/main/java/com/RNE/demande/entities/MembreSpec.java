package com.RNE.demande.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.RNE.demande.enums.Genre;
import com.RNE.demande.enums.Pouvoirs;
import com.RNE.demande.enums.Qualite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

	private String email;

	private int numero;

	private String adresse;

	@ManyToOne
	private Societe societe;

	@ManyToMany
	private List<Personne> personnes;

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
