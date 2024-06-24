package com.RNE.referentiel.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.RNE.referentiel.enums.Activation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Section implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -492407437337641766L;
	/**
	* 
	*/

	@Id
	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "titre_fr", nullable = false)

	private String titreFr;

	@Column(name = "titre_ar", nullable = false)

	private String titreAr;

	@Enumerated(EnumType.STRING)
	@Column(name = "activation", nullable = false)

	private Activation activation;

	@ManyToMany(mappedBy = "sections")

	private Set<Statut> statut;

	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL)

	private List<Article> articles;

}