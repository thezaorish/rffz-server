package com.bunker.rffz.service.publisher.impl;

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
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.domain.feedarticle.Article;
import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.domain.feedarticle.FeedSource;
import com.bunker.rffz.domain.feedarticle.dto.FeedItem;
import com.bunker.rffz.domain.feedarticle.dto.FeedItemList;
import com.bunker.rffz.domain.publisher.FeedItemMatcher;
import com.bunker.rffz.service.feedarticle.ArticleService;
import com.bunker.rffz.service.feedarticle.FeedItemService;
import com.bunker.rffz.service.feedarticle.impl.FeedItemServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FeedItemServiceImplTest {

	private FeedItemService feedItemService;

	@Mock
	private ArticleService articleService;

	@Before
	public void setUp() {
		feedItemService = new FeedItemServiceImpl(articleService);
	}

	@Test
	public void shouldGetFeedItemsPaginated() {
		// given
		int page = 2;
		int size = 5;

		List<Article> articles = new ArrayList<Article>(Arrays.asList(getTestArticle()));
		given(articleService.getArticles(page, size)).willReturn(articles);

		// when
		FeedItemList result = feedItemService.getFeedItemList(page, size);

		// then
		verify(articleService).getArticles(page, size);
		assertThat(new FeedItemListMatcher(articles).matches(result), is(true));
	}

	private Article getTestArticle() {
		FeedSource feedSource = new FeedSource("name", "url");
		Candidate candidate = new Candidate("name", "description", new Date(1000), "www.link.com", feedSource);
		candidate.setImagePath("imagePath");
		return new Article(candidate);
	}

	class FeedItemListMatcher extends ArgumentMatcher<FeedItemList> {

		private List<Article> articles;

		public FeedItemListMatcher(List<Article> articles) {
			this.articles = articles;
		}

		@Override
		public boolean matches(Object argument) {
			FeedItemList feedItemList = (FeedItemList) argument;

			for (FeedItem feedItem : feedItemList.getFeedItems()) {
				int index = feedItemList.getFeedItems().indexOf(feedItem);
				Article article = articles.get(index);

				if (!new FeedItemMatcher(article).matches(feedItem)) {
					return false;
				}
			}

			return true;
		}
	}

}
