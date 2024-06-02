package com.RNE.referentiel.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.RNE.referentiel.dto.SectionDTO;
import com.RNE.referentiel.dto.mappers.ArticleMapper;
import com.RNE.referentiel.dto.mappers.SectionMapper;
import com.RNE.referentiel.dto.mappers.StatutMapper;
import com.RNE.referentiel.entities.Section;
import com.RNE.referentiel.repositories.SectionRepository;
import com.RNE.referentiel.services.SectionService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

	private SectionRepository sectionRepo;
	private SectionMapper sectionMapper;
	private StatutMapper statutMapper;
	private ArticleMapper articleMapper;

	// service for creating a new section
	@Override
	public SectionDTO saveSection(SectionDTO sectionDTO) {
		Section section = sectionMapper.toEntity(sectionDTO);
		section = sectionRepo.save(section);

		return sectionMapper.toDto(section);
	}

	// get list of section
	@Override
	public List<SectionDTO> getAllSections() {

		List<Section> sections = sectionRepo.findAll();
		return sections.stream().map(sectionMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public SectionDTO getSectionByCode(String code) {
		Optional<Section> section = sectionRepo.findById(code);
		return section.map(sectionMapper::toDto).orElse(null);
	}

	// update section service
	@Override
	public SectionDTO updateSection(String code, SectionDTO sectionDTO) {

		Section existSection = sectionRepo.findById(code).orElse(null);
		if (existSection == null) {
			return null;
		}

		existSection.setTitreFr(sectionDTO.getTitreAr());
		existSection.setTitreAr(sectionDTO.getTitreAr());
		existSection.setActivation(sectionDTO.getActivation());
		existSection.setStatut(sectionDTO.getStatut().stream().map(statutMapper::toEntity).collect(Collectors.toSet()));
		existSection.setArticles(
				sectionDTO.getArticles().stream().map(articleMapper::toEntity).collect(Collectors.toList()));

		sectionRepo.save(existSection);

		return sectionMapper.toDto(existSection);
	}

	@Override
	public List<SectionDTO> getActivatedSection() {

		List<Section> sections = sectionRepo.getActivatedSection();
		return sections.stream().map(sectionMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public void deleteSection(String code) {
		sectionRepo.deleteById(code);
	}

}