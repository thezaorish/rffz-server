package com.bunker.rffz.service.analyser.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.bunker.rffz.domain.analyser.Article;
import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.domain.retriever.FeedSource;
import com.bunker.rffz.repository.analyser.ArticleRepository;
import com.bunker.rffz.service.analyser.ArticleService;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {

	@Mock
	private ArticleRepository articleRepository;

	private ArticleService articleService;

	@Before
	public void setUp() {
		articleService = new ArticleServiceImpl(articleRepository);
	}

	@Test
	public void shouldCreateArticle() {
		// given a valid candidate
		FeedSource feedSource = new FeedSource("name", "url");
		Candidate candidate = new Candidate("name", "description", new Date(1000), "link", feedSource);

		// when
		articleService.createArticle(candidate);

		// then
		verify(articleRepository).save(Matchers.argThat(new ArticleMatcher(candidate)));
	}

	class ArticleMatcher extends ArgumentMatcher<Article> {

		private Candidate candidate;

		public ArticleMatcher(Candidate candidate) {
			this.candidate = candidate;
		}

		@Override
		public boolean matches(Object argument) {
			if (!(argument instanceof Article)) {
				return false;
			}
			Article article = (Article) argument;

			if (!article.getCandidate().equals(candidate)) {
				return false;
			}
			if (article.getCreationDate() == null) {
				return false;
			}

			return true;
		}

	}

	@Test
	public void shouldGetArticlesPaginated() {
		// given
		int page = 2;
		int size = 5;

		List<Article> articles = new ArrayList<Article>(Arrays.asList(getTestArticle()));
		Page<Article> articleInPage = new PageImpl<Article>(articles);
		given(articleRepository.findAll(new PageRequest(page, size))).willReturn(articleInPage);

		// when
		List<Article> retrievedArticles = articleService.getArticles(page, size);

		// then
		assertThat(retrievedArticles, is(articles));
	}

	private Article getTestArticle() {
		FeedSource feedSource = new FeedSource("name", "url");
		Candidate candidate = new Candidate("name", "description", new Date(1000), "www.link.com", feedSource);
		candidate.setImagePath("imagePath");
		return new Article(candidate);
	}

}
