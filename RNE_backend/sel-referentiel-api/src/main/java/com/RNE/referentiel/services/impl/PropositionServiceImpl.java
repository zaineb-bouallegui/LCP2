package com.RNE.referentiel.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.RNE.referentiel.dto.PropositionDTO;
import com.RNE.referentiel.dto.mappers.PropositionMapper;
import com.RNE.referentiel.entities.Proposition;
import com.RNE.referentiel.repositories.PropositionRepository;
import com.RNE.referentiel.services.PropositionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PropositionServiceImpl implements PropositionService {

    private PropositionRepository propositionRepository;
    private PropositionMapper propositionMapper;

    // save proposal service
    @Override
    public PropositionDTO saveProposition(PropositionDTO propositionDTO) {

        Proposition proposition = propositionMapper.toEntity(propositionDTO);
        return propositionMapper.toDto(propositionRepository.save(proposition));
    }

    // get proposal by code service
    @Override
    public PropositionDTO getPropositionByCode(String code) {
        Optional<Proposition> existProposal = propositionRepository.findById(code);
        return existProposal.map(propositionMapper::toDto).orElse(null);
    }

    // get all proposals services
    @Override
    public List<PropositionDTO> getAllPropositions() {

        return propositionRepository.findAll().stream()
                .map(propositionMapper::toDto)
                .collect(Collectors.toList());
    }

    // update proposal service
    @Override
    public PropositionDTO updateProposition(String code, PropositionDTO propositionDTO) {
        Proposition existProposal = propositionRepository.findById(code).orElse(null);
        if (existProposal == null) {
            return null;
        }

        existProposal.setTexteFr(propositionDTO.getTexteFr());
        existProposal.setTexteAr(propositionDTO.getTexteAr());
        existProposal.setActivation(propositionDTO.getActivation());

        return propositionMapper.toDto(propositionRepository.save(existProposal));
    }

    @Override
    public void deleteProposition(String proposalCode) {
        propositionRepository.deleteById(proposalCode);
    }
}