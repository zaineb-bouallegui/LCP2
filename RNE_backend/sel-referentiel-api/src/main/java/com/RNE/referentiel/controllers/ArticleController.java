package com.RNE.referentiel.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RNE.referentiel.dto.ArticleDTO;
import com.RNE.referentiel.services.ArticleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/referentiel/articles")
@AllArgsConstructor
public class ArticleController {

	private ArticleService articleService;

	@PostMapping
	public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {

		ArticleDTO savedArticleDTO = articleService.saveArticle(articleDTO);
		return new ResponseEntity<>(savedArticleDTO, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ArticleDTO>> getAllArticles() {
		return new ResponseEntity<List<ArticleDTO>>(articleService.getAllArticles(), HttpStatus.OK);
	}

	@GetMapping("{code}")
	public ResponseEntity<ArticleDTO> getArticleByCode(@PathVariable String code) {
		return new ResponseEntity<ArticleDTO>(articleService.getArticleByCode(code), HttpStatus.OK);

	}

	@PutMapping("update/{code}")
	public ResponseEntity<ArticleDTO> updateArticle(@PathVariable String code, @RequestBody ArticleDTO articleDTO) {
		return new ResponseEntity<ArticleDTO>(articleService.updateArticle(code, articleDTO), HttpStatus.OK);
	}

	@DeleteMapping("delete/{code}")
	public ResponseEntity<String> deleteArticle(@PathVariable String code) {
		articleService.deleteArticle(code);
		return new ResponseEntity<String>("Article successfully deleted!", HttpStatus.OK);
	}
}