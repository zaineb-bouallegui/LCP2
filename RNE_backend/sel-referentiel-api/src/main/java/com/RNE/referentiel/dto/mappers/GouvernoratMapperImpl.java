package com.RNE.referentiel.dto.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RNE.referentiel.dto.VilleDTO;
import com.RNE.referentiel.dto.GouvernoratDTO;
import com.RNE.referentiel.entities.Ville;
import com.RNE.referentiel.entities.Gouvernorat;

@Component
public class GouvernoratMapperImpl implements GouvernoratMapper {

	private static final GouvernoratMapper INSTANCE = Mappers.getMapper(GouvernoratMapper.class);
	@Autowired
	private VilleMapper villeMapper;

	@Override
	public GouvernoratDTO toDto(Gouvernorat gouvernorat) {
		if (gouvernorat == null) {
			return null;
		}

		GouvernoratDTO gouvernoratDTO = new GouvernoratDTO();
		gouvernoratDTO.setCode(gouvernorat.getCode());
		gouvernoratDTO.setNomFr(gouvernorat.getNomFr());
		gouvernoratDTO.setNomAr(gouvernorat.getNomAr());
		gouvernoratDTO.setActivation(gouvernorat.getActivation());

		// Populate CityDTO objects
		List<Ville> villeEntities = gouvernorat.getVilles();
		if (villeEntities != null) {
			
			gouvernoratDTO.setVilles(villeEntities.stream().map(villeMapper::toDto).collect(Collectors.toList()));
		}

		return gouvernoratDTO;
	}

	@Override
	public Gouvernorat toEntity(GouvernoratDTO gouvernoratDTO) {
		if (gouvernoratDTO == null) {
			return null;
		}

		Gouvernorat gouvernorat = new Gouvernorat();
		gouvernorat.setCode(gouvernoratDTO.getCode());
		gouvernorat.setNomFr(gouvernoratDTO.getNomFr());
		gouvernorat.setNomAr(gouvernoratDTO.getNomAr());
		gouvernorat.setActivation(gouvernoratDTO.getActivation());

		// Populate cities objects
		List<VilleDTO> villeDTOs = gouvernoratDTO.getVilles();
		if (villeDTOs != null) {

			gouvernorat.setVilles(villeDTOs.stream().map(villeMapper::toEntity).collect(Collectors.toList()));
		}

		return gouvernorat;
	}

	public static GouvernoratMapper getInstance() {
		return INSTANCE;
	}
}