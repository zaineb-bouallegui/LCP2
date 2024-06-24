package com.RNE.demande.entities;
import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	
	@Column(name = "action_En_Nature")
	private int actionEnNature;
	
	@Column(name = "action_En_Numeraire")
	private int actionEnNumeraire;
	

	@Column(name = "valeur_Nominale_Part")
	private float valeurNominalePart ;

	@Column(name = "nbr_Parts")
	private int nbrParts ;
	
	@Column(name = "total_des_Actions")
	private float totaldesActions ;
	
	@Column(name = "rapport")
	private float rapport ;
	
	
	@ManyToOne
	private Societe societe;
	
	
   @ManyToMany(cascade = CascadeType.ALL , mappedBy = "actions")
   private Set<Personne> personnes;

}
