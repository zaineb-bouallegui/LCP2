package com.RNE.referentiel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodePostalDTO {

    private Long id;

    private String codPostal;

    private VilleDTO ville;

}