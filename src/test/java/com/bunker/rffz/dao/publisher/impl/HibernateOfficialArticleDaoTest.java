package com.bunker.rffz.dao.publisher.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.dao.publisher.OfficialArticleDao;
import com.bunker.rffz.domain.publisher.OfficialArticle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/unitTest-context.xml")
@Transactional
public class HibernateOfficialArticleDaoTest {

	@Autowired
	private OfficialArticleDao officialArticleDao;
	
	@Test
	public void shouldGetOfficialArticlesPaginated() {
		// given
		OfficialArticle officialArticle1 = new OfficialArticle().creationDate(new Date(1000))
				.title("title").description("description").content("content").thumbnail("thumbnail").image("image").published("published");
		officialArticleDao.save(officialArticle1);
		
		OfficialArticle officialArticle2 = new OfficialArticle().creationDate(new Date(0))
				.title("title").description("description").content("content").thumbnail("thumbnail").image("image").published("published");
		officialArticleDao.save(officialArticle2);
		
		OfficialArticle officialArticle3 = new OfficialArticle().creationDate(new Date(999999))
				.title("title").description("description").content("content").thumbnail("thumbnail").image("image").published("published");
		officialArticleDao.save(officialArticle3);
		
		// when
		List<OfficialArticle> articles = officialArticleDao.getOfficialArticles(1, 2);
		
		// then
		assertThat(articles, is(Arrays.asList(officialArticle3, officialArticle1)));
	}
	@Test
	public void shouldGetOfficialArticlesPaginated2() {
		// given
		OfficialArticle officialArticle1 = new OfficialArticle().creationDate(new Date())
				.title("title").description("description").content("content").thumbnail("thumbnail").image("image").published("published");
		officialArticleDao.save(officialArticle1);
		
		OfficialArticle officialArticle2 = new OfficialArticle().creationDate(new Date(0))
				.title("title").description("description").content("content").thumbnail("thumbnail").image("image").published("published");
		officialArticleDao.save(officialArticle2);
		
		OfficialArticle officialArticle3 = new OfficialArticle().creationDate(new Date(999999))
				.title("title").description("description").content("content").thumbnail("thumbnail").image("image").published("published");
		officialArticleDao.save(officialArticle3);
		
		// when
		List<OfficialArticle> articles = officialArticleDao.getOfficialArticles(2, 2);
		
		// then
		assertThat(articles, is(Arrays.asList(officialArticle2)));
	}
	
}
