package com.RNE.referentiel.dto;

import java.util.List;
import com.RNE.referentiel.enums.Activation;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {

    private String code;
    
    private String titreFr;
    
    private String titreAr;
    
    private Activation activation;
    
    private String codeSection;
    
    private List<PropositionDTO> proposition;

  
}