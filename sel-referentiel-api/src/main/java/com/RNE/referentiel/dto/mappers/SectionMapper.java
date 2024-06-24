package com.RNE.referentiel.dto.mappers;

import org.mapstruct.Mapper;

import com.RNE.referentiel.dto.SectionDTO;
import com.RNE.referentiel.entities.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    SectionDTO toDto(Section section);

    Section toEntity(SectionDTO sectionDTO);
}