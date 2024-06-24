package com.RNE.referentiel.services;

import java.util.List;

import com.RNE.referentiel.dto.PropositionDTO;

public interface PropositionService {

    public PropositionDTO saveProposition(PropositionDTO propositionDTO);

    public PropositionDTO getPropositionByCode(String code);

    public List<PropositionDTO> getAllPropositions();

    public PropositionDTO updateProposition(String code, PropositionDTO propositionDTO);

    public void deleteProposition(String code);

}