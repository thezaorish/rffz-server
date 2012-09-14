package com.bunker.rffz.service.analyser;

import java.util.List;

import com.bunker.rffz.domain.analyser.Article;
import com.bunker.rffz.domain.retriever.Candidate;

public interface ArticleService {

	void createArticle(Candidate candidate);

	List<Article> getArticles(int page, int size);

}
