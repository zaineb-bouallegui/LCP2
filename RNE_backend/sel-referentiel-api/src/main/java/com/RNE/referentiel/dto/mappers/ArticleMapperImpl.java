package com.RNE.referentiel.dto.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RNE.referentiel.dto.ArticleDTO;
import com.RNE.referentiel.dto.PropositionDTO;
import com.RNE.referentiel.entities.Article;
import com.RNE.referentiel.entities.Proposition;
import com.RNE.referentiel.entities.Section;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ArticleMapperImpl implements ArticleMapper {

	private static final ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
	@Autowired
	private PropositionMapper propositionMapper;
	
	public ArticleMapperImpl(PropositionMapper propositionMapper) {
        this.propositionMapper = propositionMapper;
    }

	@Override
	public ArticleDTO toDto(Article article) {
		if (article == null) {
			return null;
		}

		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setCode(article.getCode());
		articleDTO.setTitreFr(article.getTitreFr());
		articleDTO.setTitreAr(article.getTitreAr());
		articleDTO.setActivation(article.getActivation());

		// Populate SectionDTO object
		Section section = article.getSection();
		if (section != null) {
			articleDTO.setCodeSection(section.getCode());
		}

		// Populate ProposalDTO objects
		List<Proposition> propositions = article.getProposition();
		if (propositions != null) {
			articleDTO.setProposition(propositions.stream().map(propositionMapper::toDto).collect(Collectors.toList()));
		}

		return articleDTO;
	}

	@Override
	public Article toEntity(ArticleDTO articleDTO) {
		if (articleDTO == null) {
			return null;
		}

		Article article = new Article();
		article.setCode(articleDTO.getCode());
		article.setTitreFr(articleDTO.getTitreFr());
		article.setTitreAr(articleDTO.getTitreAr());
		article.setActivation(articleDTO.getActivation());

		// Populate Section object
		String codeSection = articleDTO.getCodeSection();
		if (codeSection != null) {
			Section section = new Section();
			section.setCode(codeSection);
			article.setSection(section);
		}

		// Populate Proposal objects
		List<PropositionDTO> propositionDTOs = articleDTO.getProposition();
		if (propositionDTOs != null) {
			article.setProposition(propositionDTOs.stream().map(propositionMapper::toEntity).collect(Collectors.toList()));

		}

		return article;
	}

	public static ArticleMapper getInstance() {
		return INSTANCE;
	}
}