package com.RNE.referentiel.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.RNE.referentiel.dto.ArticleDTO;
import com.RNE.referentiel.dto.mappers.ArticleMapper;
import com.RNE.referentiel.entities.Article;
import com.RNE.referentiel.repositories.ArticleRepository;
import com.RNE.referentiel.services.ArticleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private ArticleMapper articleMapper;
  
    
    // save article service
    @Override
    public ArticleDTO saveArticle(ArticleDTO articleDTO) {

        Article article = articleMapper.toEntity(articleDTO);
        return articleMapper.toDto(articleRepository.save(article));
    }

    // get article by code service
    @Override
    public ArticleDTO getArticleByCode(String code) {
        Optional<Article> existArticle = articleRepository.findById(code);
        return existArticle.map(articleMapper::toDto).orElse(null);
    }

    // get all articles services
    @Override
    public List<ArticleDTO> getAllArticles() {

        return articleRepository.findAll().stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    // update article service
    @Override
    public ArticleDTO updateArticle(String code, ArticleDTO articleDTO) {
        Article existArticle = articleRepository.findById(code).orElse(null);
        if (existArticle == null) {
            return null;
        }

        existArticle.setTitreFr(articleDTO.getTitreFr());
        existArticle.setTitreAr(articleDTO.getTitreAr());
        existArticle.setActivation(articleDTO.getActivation());

        return articleMapper.toDto(articleRepository.save(existArticle));
    }

    @Override
    public void deleteArticle(String code) {
        articleRepository.deleteById(code);
    }
}