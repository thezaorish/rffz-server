package com.bunker.rffz.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.domain.officialarticle.dto.OfficialArticle;
import com.bunker.rffz.domain.officialarticle.dto.OfficialArticleList;
import com.bunker.rffz.service.officialarticle.OfficialArticleService;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class OfficialArticleControllerTest {

	private OfficialArticleController controller;
	
	@Mock
	private OfficialArticleService officialArticleService;
	
	@Before
	public void setUp() {
		controller = new OfficialArticleController(officialArticleService);
	}
	
	@Test
	public void shouldGetPaginatedOfficialArticles() {
		// given some feed items
		int page = 2;
		int size = 5;

		OfficialArticleList officialArticleList = new OfficialArticleList(Lists.newArrayList(new OfficialArticle()));
		given(officialArticleService.getOfficialArticlesList(page, size)).willReturn(officialArticleList);

		// when
		OfficialArticleList returned = controller.getPaginatedOfficialArticles(page, size);

		// then
		verify(officialArticleService).getOfficialArticlesList(page, size);
		assertThat(returned, is(officialArticleList));
	}
	
}
