package com.RNE.referentiel.dto.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RNE.referentiel.dto.VilleDTO;
import com.RNE.referentiel.dto.CodePostalDTO;
import com.RNE.referentiel.entities.Ville;
import com.RNE.referentiel.entities.CodePostal;
import com.RNE.referentiel.repositories.VilleRepository;

@Component
public class CodePostalMapperImpl implements CodePostalMapper {

	@Autowired
	private VilleRepository villeRepository;

	@Override
	public CodePostalDTO toDto(CodePostal codePostal) {

		if (codePostal == null) {
			return null;
		}

		CodePostalDTO codePostalDTO = new CodePostalDTO();
		codePostalDTO.setId(codePostal.getId());
		codePostalDTO.setCodPostal(codePostal.getCodePostal());

		Ville ville = codePostal.getVille();
		if (ville != null) {
			codePostalDTO.setVille(new VilleDTO(ville.getCode(), ville.getNomFr(), ville.getNomAr(), ville.getActivation(),
					ville.getGouvernorat().getCode()));
		}

		return codePostalDTO;
	}

	@Override
	public CodePostal toEntity(CodePostalDTO codePostalDTO) {

		if (codePostalDTO == null) {
			return null;
		}

		CodePostal codePostal = new CodePostal();
		codePostal.setId(codePostalDTO.getId());
		codePostal.setCodePostal(codePostalDTO.getCodPostal());

		String villeCode = codePostalDTO.getVille().getCode();
		if (villeCode != null) {
			Ville ville = villeRepository.findById(villeCode).orElse(null);
			codePostal.setVille(ville);
		}

		return codePostal;
	}

}