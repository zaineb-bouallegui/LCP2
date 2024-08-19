package com.RNE.referentiel.dto;


import com.RNE.referentiel.enums.Activation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropositionDTO {

    private String code;
    
    private String texteFr;
    
    private String texteAr;
  
    private Activation activation;
    
    private String articleCode;


}