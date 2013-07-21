package com.bunker.rffz.service.feedarticle.analyser.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

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

import com.bunker.rffz.dao.feedarticle.ArticleDao;
import com.bunker.rffz.domain.feedarticle.Article;
import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.domain.feedarticle.FeedSource;
import com.bunker.rffz.service.feedarticle.analyser.ArticleService;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {

	@Mock
	private ArticleDao articleDao;

	private ArticleService articleService;

	@Before
	public void setUp() {
		articleService = new ArticleServiceImpl(articleDao);
	}

	@Test
	public void shouldCreateArticle() {
		// given a valid candidate
		FeedSource feedSource = new FeedSource("name", "url");
		Candidate candidate = new Candidate("name", "description", new Date(1000), "link", feedSource);

		// when
		articleService.createArticle(candidate);

		// then
		verify(articleDao).save(Matchers.argThat(new ArticleMatcher(candidate)));
	}
    @Test
    public void shouldNotCreateArticleFromInvalidCandidate_emptyName() {
        // given an invalid candidate
        FeedSource feedSource = new FeedSource("name", "url");
        Candidate candidate = new Candidate("name", "", new Date(1000), "link", feedSource);

        // when
        articleService.createArticle(candidate);

        // then
        verify(articleDao, never()).save(any(Article.class));
    }
    @Test
    public void shouldNotCreateArticleFromInvalidCandidate_nullName() {
        // given an invalid candidate
        FeedSource feedSource = new FeedSource("name", "url");
        Candidate candidate = new Candidate("name", null, new Date(1000), "link", feedSource);

        // when
        articleService.createArticle(candidate);

        // then
        verify(articleDao, never()).save(any(Article.class));
    }
    @Test
    public void shouldNotCreateArticleFromInvalidCandidate_emptyDescription() {
        // given an invalid candidate
        FeedSource feedSource = new FeedSource("name", "url");
        Candidate candidate = new Candidate("name", "", new Date(1000), "link", feedSource);

        // when
        articleService.createArticle(candidate);

        // then
        verify(articleDao, never()).save(any(Article.class));
    }
    @Test
    public void shouldNotCreateArticleFromInvalidCandidate_nullDescription() {
        // given an invalid candidate
        FeedSource feedSource = new FeedSource("name", "url");
        Candidate candidate = new Candidate("name", null, new Date(1000), "link", feedSource);

        // when
        articleService.createArticle(candidate);

        // then
        verify(articleDao, never()).save(any(Article.class));
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
		given(articleDao.getArticles(page, size)).willReturn(articles);

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
