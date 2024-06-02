package com.RNE.referentiel.dto.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RNE.referentiel.dto.ArticleDTO;
import com.RNE.referentiel.dto.SectionDTO;
import com.RNE.referentiel.dto.StatutDTO;
import com.RNE.referentiel.entities.Article;
import com.RNE.referentiel.entities.Section;
import com.RNE.referentiel.entities.Statut;

@Component
public class SectionMapperImpl implements SectionMapper {

    private static final SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);
    @Autowired
    private StatutMapper statutMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public SectionDTO toDto(Section section) {
        if (section == null) {
            return null;
        }

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setCode(section.getCode());
        sectionDTO.setTitreFr(section.getTitreFr());
        sectionDTO.setTitreAr(section.getTitreAr());
        sectionDTO.setActivation(section.getActivation());

        // Populate StatusDTO objects
        Set<Statut> statusEntities = section.getStatut();
        if (statusEntities != null) {
            sectionDTO.setStatut(statusEntities.stream()
                .map(statutMapper::toDto)
                .collect(Collectors.toSet()));;
        }

        // Populate ArticleDTO objects
        List<Article> articleEntities = section.getArticles();
        if (articleEntities != null) {
            sectionDTO.setArticles(articleEntities.stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList()));
        }

        return sectionDTO;
    }

    @Override
    public Section toEntity(SectionDTO sectionDTO) {
        if (sectionDTO == null) {
            return null;
        }

        Section section = new Section();
        section.setCode(sectionDTO.getCode());
        section.setTitreFr(sectionDTO.getTitreAr());
        section.setTitreAr(sectionDTO.getTitreAr());
        section.setActivation(sectionDTO.getActivation());

        // Populate Status objects
        Set<StatutDTO> statutDTOs = sectionDTO.getStatut();
        if (statutDTOs != null) {
            section.setStatut(statutDTOs.stream()
                .map(statutMapper::toEntity)
                .collect(Collectors.toSet()));
        }

        // Populate Article objects
        List<ArticleDTO> articleDTOs = sectionDTO.getArticles();
        if (articleDTOs != null) {
            section.setArticles(articleDTOs.stream()
                .map(articleMapper::toEntity)
                .collect(Collectors.toList()));
        }

        return section;
    }

    public static SectionMapper getInstance() {
        return INSTANCE;
    }
}