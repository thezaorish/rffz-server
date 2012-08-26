package com.bunker.rffz.dao.analyser.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
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

import com.bunker.rffz.dao.analyser.ArticleDao;
import com.bunker.rffz.dao.retriever.CandidateDao;
import com.bunker.rffz.dao.retriever.FeedSourceDao;
import com.bunker.rffz.domain.analyser.Article;
import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.domain.retriever.FeedSource;

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

	@Test
	public void shouldGetArticleNewerThanSpecifiedDate() {
		// given an article just created
		articleDao.save(article);

		// and an article created 2 months ago
		Article articleCreatedTwoMonthsAgo = new Article(candidate);
		articleCreatedTwoMonthsAgo.setCreationDate(getDateXMonthsAgo(2));
		articleDao.save(articleCreatedTwoMonthsAgo);

		// and an article created 1 month ago
		Date oneMonthAgo = getDateXMonthsAgo(1);

		Article articleCreatedOneMonthAgo = new Article(candidate);
		articleCreatedOneMonthAgo.setCreationDate(oneMonthAgo);
		articleDao.save(articleCreatedOneMonthAgo);

		// when we retrieve the articles newer than 1 month
		List<Article> articlesNewerThanOneMonth = articleDao.getArticlesNewerThan(oneMonthAgo);

		// then
		assertThat(articlesNewerThanOneMonth.size(), is(2));
		assertThat(articlesNewerThanOneMonth.get(0), is(article));
		assertThat(articlesNewerThanOneMonth.get(1), is(articleCreatedOneMonthAgo));
	}

	@Test
	public void shouldGetArticlesWithMaxDate() {
		// given an article created 1 month
		Date oneMonthAgo = getDateXMonthsAgo(1);

		article.setCreationDate(oneMonthAgo);
		articleDao.save(article);

		// and an article created 2 months ago
		Article articleCreatedTwoMonthsAgo = new Article(candidate);
		articleCreatedTwoMonthsAgo.setCreationDate(getDateXMonthsAgo(2));
		articleDao.save(articleCreatedTwoMonthsAgo);

		// and an article created 1 month ago
		Article articleCreatedOneMonthAgo = new Article(candidate);
		articleCreatedOneMonthAgo.setCreationDate(oneMonthAgo);
		articleDao.save(articleCreatedOneMonthAgo);

		// when we retrieve the articles newer than 1 month
		List<Article> articlesWithMaxCreationDate = articleDao.getArticlesWithMaxCreationDate();

		// then
		assertThat(articlesWithMaxCreationDate.size(), is(2));
		assertThat(articlesWithMaxCreationDate.get(0), is(article));
		assertThat(articlesWithMaxCreationDate.get(1), is(articleCreatedOneMonthAgo));
	}

	private Date getDateXMonthsAgo(int numberOfMonths) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -numberOfMonths);
		return cal.getTime();
	}

}
