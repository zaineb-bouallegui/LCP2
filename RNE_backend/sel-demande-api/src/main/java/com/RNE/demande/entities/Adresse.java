package com.RNE.demande.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.RNE.demande.enums.AdresseType;
import com.RNE.demande.enums.Genre;
import com.RNE.demande.enums.TypeIdCarte;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Adresse implements Serializable{

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

	    @Column(name = "gov_Code")
	    private String govCode;

	    @Column(name = "code_Ville")
	    private String codeVille;

	    @Column(name = "code_Postal")
	    private String codePostal;
	    
	    @Column(name = "rue_Fr")
	    private String rueFr;
	    
	    
	    @Column(name = "rue_Ar")
	    private String rueAr;
	    
	    @ManyToOne
	    private Societe societe;
	    
	    @OneToOne(mappedBy = "adresse")
	    private Personne personne;
	    

		@Enumerated(EnumType.STRING)
		@Column(name = "adresse_type")
		private AdresseType adresseType;


}
