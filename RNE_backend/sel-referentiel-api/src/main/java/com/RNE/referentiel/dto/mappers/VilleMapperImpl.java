package com.RNE.referentiel.dto.mappers;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.RNE.referentiel.dto.VilleDTO;
import com.RNE.referentiel.entities.Ville;
import com.RNE.referentiel.entities.Gouvernorat;

@Component
public class VilleMapperImpl implements VilleMapper {

	private static final VilleMapper INSTANCE = Mappers.getMapper(VilleMapper.class);

	@Override
	public VilleDTO toDto(Ville ville) {
		if (ville == null) {
			return null;
		}

		VilleDTO villeDTO = new VilleDTO();
		villeDTO.setCode(ville.getCode());
		villeDTO.setNomFr(ville.getNomFr());
		villeDTO.setNomAr(ville.getNomAr());
		villeDTO.setActivation(ville.getActivation());

		// Populate delegationDTO object
		Gouvernorat gouvernorat = ville.getGouvernorat();
		if (gouvernorat != null) {
			villeDTO.setGouverneratCode(gouvernorat.getCode());
		}

		return villeDTO;
	}

	@Override
	public Ville toEntity(VilleDTO villeDTO) {
		if (villeDTO == null) {
			return null;
		}

		Ville ville = new Ville();
		ville.setCode(villeDTO.getCode());
		ville.setNomFr(villeDTO.getNomFr());
		ville.setNomAr(villeDTO.getNomAr());
		ville.setActivation(villeDTO.getActivation());

		// Populate Delegation object
		String gouverneratCode = villeDTO.getGouverneratCode();
		if (gouverneratCode != null) {
			Gouvernorat gouvernorat = new Gouvernorat();
			gouvernorat.setCode(gouverneratCode);
			ville.setGouvernorat(gouvernorat);
		}

		return ville;
	}

	public static VilleMapper getInstance() {
		return INSTANCE;
	}
}