package com.RNE.referentiel.dto.mappers;

import org.mapstruct.Mapper;

import com.RNE.referentiel.dto.GouvernoratDTO;
import com.RNE.referentiel.entities.Gouvernorat;

@Mapper(componentModel = "spring")
public interface GouvernoratMapper {

	GouvernoratDTO toDto(Gouvernorat gouvernorat);

	Gouvernorat toEntity(GouvernoratDTO gouvernoratDTO);
}