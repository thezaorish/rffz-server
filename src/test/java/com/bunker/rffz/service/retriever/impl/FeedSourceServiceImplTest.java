package com.bunker.rffz.service.retriever.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.dao.feedarticle.FeedSourceDao;
import com.bunker.rffz.domain.feedarticle.FeedSource;
import com.bunker.rffz.service.feedarticle.FeedSourceService;
import com.bunker.rffz.service.feedarticle.impl.FeedSourceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FeedSourceServiceImplTest {

	private FeedSourceService feedSourceService;

	@Mock
	private FeedSourceDao feedSourceDao;

	@Before
	public void setUp() {
		feedSourceService = new FeedSourceServiceImpl(feedSourceDao);
	}

	@Test
	public void shouldGetAllFeedSources() {
		// given
		FeedSource feedSource = new FeedSource("name", "url");
		List<FeedSource> feedSources = new ArrayList<FeedSource>(Arrays.asList(feedSource));
		given(feedSourceDao.getAllFeedSources()).willReturn(feedSources);

		// when
		List<FeedSource> expectedFeedSources = feedSourceService.getAllFeedSources();

		// then
		assertThat(expectedFeedSources, is(feedSources));
	}

}
