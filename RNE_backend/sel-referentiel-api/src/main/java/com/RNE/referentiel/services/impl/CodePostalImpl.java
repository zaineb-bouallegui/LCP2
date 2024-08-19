package com.RNE.referentiel.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.RNE.referentiel.dto.CodePostalDTO;
import com.RNE.referentiel.dto.mappers.VilleMapper;
import com.RNE.referentiel.dto.mappers.CodePostalMapper;
import com.RNE.referentiel.entities.CodePostal;
import com.RNE.referentiel.repositories.CodePostalRepository;
import com.RNE.referentiel.services.CodePostalService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CodePostalImpl implements CodePostalService {

	private CodePostalRepository codePostalRepo;
	private CodePostalMapper codePostalMapper;
	private VilleMapper villeMapper;

	@Override
	public CodePostalDTO saveCodePostal(CodePostalDTO codePostalDTO) {

		return codePostalMapper.toDto(codePostalRepo.save(codePostalMapper.toEntity(codePostalDTO)));
	}

	@Override
	public CodePostalDTO getCodePostalById(Long id) {

		CodePostal existPostalCode = codePostalRepo.findById(id).orElse(null);
		return codePostalMapper.toDto(existPostalCode);
	}

	@Override
	public List<CodePostalDTO> getAllCodePostal() {

		return codePostalRepo.findAll().stream().map(codePostalMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public CodePostalDTO updateCodePostal(Long id, CodePostalDTO codePostalDTO) {

		CodePostal existCodePostal = codePostalRepo.findById(id).orElse(null);
		if (existCodePostal == null) {
			return null;
		}

		if (codePostalDTO.getCodPostal() != null) {
			existCodePostal.setCodePostal(codePostalDTO.getCodPostal());
		}

		if (codePostalDTO.getVille() != null) {
			existCodePostal.setVille(villeMapper.toEntity(codePostalDTO.getVille()));
		}

		return codePostalMapper.toDto(codePostalRepo.save(existCodePostal));
	}

	@Override
	public void deleteCodePostal(Long id) {

		codePostalRepo.deleteById(id);

	}

}