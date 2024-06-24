package com.RNE.referentiel.dto;

import com.RNE.referentiel.enums.Activation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VilleDTO {

	private String code;

	private String nomFr;

	private String nomAr;

	private Activation activation;

	private String gouverneratCode;

}