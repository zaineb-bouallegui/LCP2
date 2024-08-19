package com.RNE.referentiel.dto.mappers;

import org.mapstruct.Mapper;

import com.RNE.referentiel.dto.PropositionDTO;
import com.RNE.referentiel.entities.Proposition;

@Mapper(componentModel = "spring")
public interface PropositionMapper {

    PropositionDTO toDto(Proposition proposition);

    Proposition toEntity(PropositionDTO propositionDTO);
}