package com.RNE.referentiel.dto.mappers;

import org.mapstruct.Mapper;

import com.RNE.referentiel.dto.VilleDTO;
import com.RNE.referentiel.entities.Ville;

@Mapper(componentModel = "spring")
public interface VilleMapper {

	VilleDTO toDto(Ville ville);

	Ville toEntity(VilleDTO villeDTO);
}