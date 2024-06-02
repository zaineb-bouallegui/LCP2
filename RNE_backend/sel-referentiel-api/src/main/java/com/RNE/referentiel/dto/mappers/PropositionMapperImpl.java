package com.RNE.referentiel.dto.mappers;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.RNE.referentiel.dto.PropositionDTO;
import com.RNE.referentiel.entities.Article;
import com.RNE.referentiel.entities.Proposition;

@Component
public class PropositionMapperImpl implements PropositionMapper {

	private static final PropositionMapper INSTANCE = Mappers.getMapper(PropositionMapper.class);

	@Override
	public PropositionDTO toDto(Proposition proposition) {
		if (proposition == null) {
			return null;
		}

		PropositionDTO propositionDTO = new PropositionDTO();
		propositionDTO.setCode(proposition.getCode());
		propositionDTO.setTexteFr(proposition.getTexteFr());
		propositionDTO.setTexteAr(proposition.getTexteAr());
		propositionDTO.setActivation(proposition.getActivation());

		// Populate articleDTO object
		Article article = proposition.getArticle();
		if (article != null) {
			propositionDTO.setArticleCode(article.getCode());
		}

		return propositionDTO;
	}

	@Override
	public Proposition toEntity(PropositionDTO propositionDTO) {
		if (propositionDTO == null) {
			return null;
		}

		Proposition proposition = new Proposition();
		proposition.setCode(propositionDTO.getCode());
		proposition.setTexteFr(propositionDTO.getTexteFr());
		proposition.setTexteAr(propositionDTO.getTexteAr());
		proposition.setActivation(propositionDTO.getActivation());

		// Populate Article object
		String codeArticle = propositionDTO.getArticleCode();
		if (codeArticle != null) {
			Article article = new Article();
			article.setCode(codeArticle);
			proposition.setArticle(article);
		}

		return proposition;
	}

	public static PropositionMapper getInstance() {
		return INSTANCE;
	}
}