package com.RNE.referentiel.services;

import java.util.List;

import com.RNE.referentiel.dto.ArticleDTO;

public interface ArticleService {

    public ArticleDTO saveArticle(ArticleDTO articleDTO);

    public ArticleDTO getArticleByCode(String code);

    public List<ArticleDTO> getAllArticles();

    public ArticleDTO updateArticle(String code, ArticleDTO articleDTO);

    public void deleteArticle(String code);

}