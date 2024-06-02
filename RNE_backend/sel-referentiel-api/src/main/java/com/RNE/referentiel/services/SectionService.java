package com.RNE.referentiel.services;

import java.util.List;

import com.RNE.referentiel.dto.SectionDTO;

public interface SectionService {

    public SectionDTO saveSection(SectionDTO sectionDTO);

    public List<SectionDTO> getAllSections();

    public SectionDTO getSectionByCode(String code);

    public SectionDTO updateSection(String code, SectionDTO sectionDTO);

    public List<SectionDTO> getActivatedSection();

    public void deleteSection(String code);
}