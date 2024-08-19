package com.RNE.referentiel.dto.mappers;

import org.mapstruct.Mapper;
import com.RNE.referentiel.dto.CodePostalDTO;
import com.RNE.referentiel.entities.CodePostal;

@Mapper(componentModel = "spring")
public interface CodePostalMapper {

	CodePostalDTO toDto(CodePostal codePostal);

	CodePostal toEntity(CodePostalDTO codePostalDTO);

}