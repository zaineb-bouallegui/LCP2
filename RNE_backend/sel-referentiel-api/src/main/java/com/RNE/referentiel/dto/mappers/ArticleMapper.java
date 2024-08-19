package com.RNE.referentiel.dto.mappers;

import org.mapstruct.Mapper;

import com.RNE.referentiel.dto.ArticleDTO;
import com.RNE.referentiel.entities.Article;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDTO toDto(Article article);

    Article toEntity(ArticleDTO articleDTO);
}