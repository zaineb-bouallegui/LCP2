package com.RNE.referentiel.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.RNE.referentiel.dto.StatutDTO;
import com.RNE.referentiel.entities.Statut;

@Mapper(componentModel = "spring")
public interface StatutMapper {

	@Mappings({ @Mapping(source = "category", target = "category") })
	StatutDTO toDto(Statut statut);

	@Mappings({ @Mapping(source = "category", target = "category") })
	Statut toEntity(StatutDTO statutDTO);

}