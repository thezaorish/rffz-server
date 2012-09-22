package com.bunker.rffz.dao.feedarticle;

import java.util.List;

import com.bunker.rffz.domain.feedarticle.Article;

public interface ArticleDao {

	void save(Article article);

	List<Article> getAllArticles();

	List<Article> getArticles(int page, int size);

}
