package com.bunker.rffz.service.feedarticle;

import java.util.List;

import com.bunker.rffz.domain.feedarticle.Article;
import com.bunker.rffz.domain.feedarticle.Candidate;

public interface ArticleService {

	void createArticle(Candidate candidate);

	List<Article> getArticles(int page, int size);

}
