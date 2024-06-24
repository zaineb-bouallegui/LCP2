package com.RNE.referentiel.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.RNE.referentiel.dto.GouvernoratDTO;
import com.RNE.referentiel.dto.mappers.GouvernoratMapper;
import com.RNE.referentiel.entities.Gouvernorat;
import com.RNE.referentiel.repositories.GouvernoratRepository;
import com.RNE.referentiel.services.GouvernoratService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GouvernoratServiceImpl implements GouvernoratService {

	private GouvernoratRepository gouvernoratRepository;
	private GouvernoratMapper gouvernoratMapper;

	// save delegation service
	@Override
	public GouvernoratDTO saveGouvernorat(GouvernoratDTO gouvernoratDTO) {

		Gouvernorat gouvernorat = gouvernoratMapper.toEntity(gouvernoratDTO);
		return gouvernoratMapper.toDto(gouvernoratRepository.save(gouvernorat));
	}

	// get delegation by code service
	@Override
	public GouvernoratDTO getGouvernoratByCode(String code) {
		Optional<Gouvernorat> existDelegation = gouvernoratRepository.findById(code);
		return existDelegation.map(gouvernoratMapper::toDto).orElse(null);
	}

	// get all delegations services
	@Override
	public List<GouvernoratDTO> getAllGouvernorats() {

		return gouvernoratRepository.findAll().stream().map(gouvernoratMapper::toDto).collect(Collectors.toList());
	}

	// update delegation service
	@Override
	public GouvernoratDTO updateGouvernorat(String code, GouvernoratDTO gouvernoratDTO) {
		Gouvernorat existGouvernerat = gouvernoratRepository.findById(code).orElse(null);
		if ( existGouvernerat == null) {
			return null;
		}

		 existGouvernerat.setNomFr(gouvernoratDTO.getNomFr());
		 existGouvernerat.setNomAr(gouvernoratDTO.getNomAr());
		 existGouvernerat.setActivation(gouvernoratDTO.getActivation());

		return gouvernoratMapper.toDto(gouvernoratRepository.save(existGouvernerat));
	}

	@Override
	public List<GouvernoratDTO> getActivatedGouvernorats() {

		return gouvernoratRepository.getActivatedGouvernerats().stream().map(gouvernoratMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteGouvernorat(String code) {
		gouvernoratRepository.deleteById(code);
	}
}