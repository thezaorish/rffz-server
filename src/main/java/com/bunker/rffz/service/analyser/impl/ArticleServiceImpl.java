package com.bunker.rffz.service.analyser.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bunker.rffz.domain.analyser.Article;
import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.repository.analyser.ArticleRepository;
import com.bunker.rffz.service.analyser.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	private static final Logger logger = Logger.getLogger(ArticleServiceImpl.class);

	private ArticleRepository articleRepository;

	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public void createArticle(Candidate candidate) {
		Article article = new Article(candidate);
		articleRepository.save(article);
		logger.info("createArticle with id: " + article.getId());
	}

	@Override
	public List<Article> getArticles(int page, int size) {
		Page<Article> articlesInPage =  articleRepository.findAll(new PageRequest(page, size));
		List<Article> articles = articlesInPage.getContent();
		return articles;
	}

}
