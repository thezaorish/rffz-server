package com.bunker.rffz.dao.analyser;

import java.util.Date;
import java.util.List;

import com.bunker.rffz.domain.analyser.Article;

public interface ArticleDao {

	void save(Article article);

	List<Article> getAllArticles();

	List<Article> getArticles(int page, int size);

	List<Article> getArticlesNewerThan(Date date);

	List<Article> getArticlesWithMaxCreationDate();

}
