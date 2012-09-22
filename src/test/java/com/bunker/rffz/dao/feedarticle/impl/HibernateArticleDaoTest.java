package com.bunker.rffz.dao.feedarticle.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.dao.feedarticle.ArticleDao;
import com.bunker.rffz.dao.feedarticle.CandidateDao;
import com.bunker.rffz.dao.feedarticle.FeedSourceDao;
import com.bunker.rffz.domain.feedarticle.Article;
import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.domain.feedarticle.FeedSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/unitTest-context.xml")
@Transactional
public class HibernateArticleDaoTest {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private FeedSourceDao feedSourceDao;

	private Article article;
	private Candidate candidate;
	private FeedSource feedSource;

	@Before
	public void setUp() {
		feedSource = new FeedSource("feedName", "feedUrl");
		feedSourceDao.save(feedSource);

		candidate = new Candidate("title", "description", new Date(), "link", feedSource);
		candidate.setImagePath("imagePath");
		candidateDao.save(candidate);

		article = new Article(candidate);
		assertThat(articleDao.getAllArticles(), is(Collections.EMPTY_LIST));
	}

	@Test
	public void shouldSaveArticle() {
		// given a configured article

		// when
		articleDao.save(article);

		// then the article in db is the one just added
		assertThat(articleDao.getAllArticles().size(), is(1));
		assertThat(articleDao.getAllArticles().get(0), is(article));
	}

	@Test
	public void shouldGetAllArticles() {
		// given a valid article
		articleDao.save(article);

		// when
		List<Article> returnedArticles = articleDao.getAllArticles();

		// then
		assertThat(article.getId(), notNullValue());
		assertThat(returnedArticles.size(), is(1));
		assertThat(returnedArticles.get(0), is(article));
	}

	@Test
	public void shouldGetArticlesPaginated() {
		// given
		Candidate candidate1 = new Candidate("title1", "description", new Date(10), "link", feedSource);
		candidateDao.save(candidate1);

		Article article1 = new Article(candidate1);
		articleDao.save(article1);

		//
		Candidate candidate2 = new Candidate("title2", "description", new Date(999999999), "link", feedSource);
		candidateDao.save(candidate2);

		Article article2 = new Article(candidate2);
		articleDao.save(article2);

		//
		Candidate candidate3 = new Candidate("title3", "description", new Date(), "link", feedSource);
		candidateDao.save(candidate3);

		Article article3 = new Article(candidate3);
		articleDao.save(article3);

		// when
		List<Article> articles = articleDao.getArticles(1, 2);

		// then
		assertThat(articles, is(Arrays.asList(article3, article2)));
	}
	@Test
	public void shouldGetArticlesPaginated2() {
		// given
		Candidate candidate1 = new Candidate("title1", "description", new Date(10), "link", feedSource);
		candidateDao.save(candidate1);

		Article article1 = new Article(candidate1);
		articleDao.save(article1);

		//
		Candidate candidate2 = new Candidate("title2", "description", new Date(999999999), "link", feedSource);
		candidateDao.save(candidate2);

		Article article2 = new Article(candidate2);
		articleDao.save(article2);

		//
		Candidate candidate3 = new Candidate("title3", "description", new Date(), "link", feedSource);
		candidateDao.save(candidate3);

		Article article3 = new Article(candidate3);
		articleDao.save(article3);

		// when
		List<Article> articles = articleDao.getArticles(2, 2);

		// then
		assertThat(articles, is(Arrays.asList(article1)));
	}

}
