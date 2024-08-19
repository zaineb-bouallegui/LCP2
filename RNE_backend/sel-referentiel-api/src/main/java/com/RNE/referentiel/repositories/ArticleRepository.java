package com.RNE.referentiel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RNE.referentiel.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {

}
