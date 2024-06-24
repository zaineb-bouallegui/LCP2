package com.RNE.referentiel.dto;

import java.util.List;

import com.RNE.referentiel.enums.Categorie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatutDTO {
	private String code;

	private String titre;

	private String description;

	private Categorie categorie;
	
	private List<String> sectionCodes;

}