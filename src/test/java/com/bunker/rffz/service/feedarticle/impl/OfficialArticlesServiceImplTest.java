package com.bunker.rffz.service.feedarticle.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.dao.officialarticle.OfficialArticleDao;
import com.bunker.rffz.domain.officialarticle.dto.OfficialArticle;
import com.bunker.rffz.domain.officialarticle.dto.OfficialArticleList;
import com.bunker.rffz.service.officialarticle.OfficialArticleService;
import com.bunker.rffz.service.officialarticle.impl.OfficialArticlesServiceImpl;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class OfficialArticlesServiceImplTest {

	private OfficialArticleService officialArticleService;
	
	@Mock
	private OfficialArticleDao officialArticleDao;
	
	@Before
	public void setUp() {
		officialArticleService = new OfficialArticlesServiceImpl(officialArticleDao);
	}
	
	@Test
	public void shouldGetOfficialArticlesPaginated()  {
		// given
		int page = 2;
		int size = 5;

		OfficialArticle officialArticle1 = new OfficialArticle().creationDate(new Date(1000))
				.title("title").description("description").content("content").thumbnail("thumbnail").image("image").published("published");
		
		OfficialArticle officialArticle2 = new OfficialArticle().creationDate(new Date(0))
				.title("title").description("description").content("content").thumbnail("thumbnail").image("image").published("published");
		
		List<OfficialArticle> officialAtticles = Lists.newArrayList(officialArticle1, officialArticle2);
		given(officialArticleDao.getOfficialArticles(page, size)).willReturn(officialAtticles);
		
		// when
		OfficialArticleList result = officialArticleService.getOfficialArticlesList(page, size);
		
		// then
		verify(officialArticleDao).getOfficialArticles(page, size);
		assertThat(result.getOfficialArticles(), is(officialAtticles));
	}
	
}
