package com.RNE.referentiel.dto.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.RNE.referentiel.dto.StatutDTO;
import com.RNE.referentiel.entities.Statut;

@Component
public class StatutMapperImpl implements StatutMapper {

	private static final StatutMapper INSTANCE = Mappers.getMapper(StatutMapper.class);

	@Override
	@Mappings({ @Mapping(source = "category", target = "category") })
	public StatutDTO toDto(Statut statut) {
		if (statut == null) {
			return null;
		}

		StatutDTO statutDTO = new StatutDTO();
		statutDTO.setCode(statut.getCode());
		statutDTO.setTitre(statut.getTitre());
		statutDTO.setDescription(statut.getDescription());
		statutDTO.setCategorie(statut.getCategorie());

		return statutDTO;
	}

	@Override
	@Mappings({ @Mapping(source = "category", target = "category") })
	public Statut toEntity(StatutDTO statutDTO) {
		if (statutDTO == null) {
			return null;
		}

		Statut statut = new Statut();
		statut.setCode(statutDTO.getCode());
		statut.setTitre(statutDTO.getTitre());
		statut.setDescription(statutDTO.getDescription());
		statut.setCategorie(statutDTO.getCategorie());

		return statut;
	}

	public static StatutMapper getInstance() {
		return INSTANCE;
	}
}