package com.RNE.referentiel.dto;

import java.util.List;

import com.RNE.referentiel.enums.Activation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GouvernoratDTO {

	private String code;

	private String nomFr;

	private String nomAr;

	private Activation activation;
	
	private List<VilleDTO> villes;

}