package com.bunker.rffz.service.analyser.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.dao.analyser.ArticleDao;
import com.bunker.rffz.domain.analyser.Article;
import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.service.analyser.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	private static final Logger logger = Logger.getLogger(ArticleServiceImpl.class);

	private ArticleDao articleDao;

	@Autowired
	public ArticleServiceImpl(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@Override
	public void createArticle(Candidate candidate) {
		Article article = new Article(candidate);
		articleDao.save(article);
		logger.info("createArticle with id: " + article.getId());
	}

	@Override
	public List<Article> getArticles(int page, int size) {
		List<Article> articles = articleDao.getArticles(page, size);
		return articles;
	}

	@Override
	public List<Article> getArticlesNewerThan(Date date) {
		List<Article> articles = articleDao.getArticlesNewerThan(date);
		return articles;
	}

	@Override
	public List<Article> getArticlesWithMaxCreationDate() {
		List<Article> articles = articleDao.getArticlesWithMaxCreationDate();
		return articles;
	}

}
