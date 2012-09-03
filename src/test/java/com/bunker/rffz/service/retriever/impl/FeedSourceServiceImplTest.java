package com.bunker.rffz.service.retriever.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.bunker.rffz.config.PersistenceContextConfig;
import com.bunker.rffz.domain.retriever.FeedSource;
import com.bunker.rffz.repository.retriever.FeedSourceRepository;
import com.bunker.rffz.service.retriever.FeedSourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceContextConfig.class }, loader = AnnotationConfigContextLoader.class)
public class FeedSourceServiceImplTest {

	private FeedSourceService feedSourceService;

	@Autowired
	private FeedSourceRepository feedSourceRepository;

	@Before
	public void setUp() {
		feedSourceService = new FeedSourceServiceImpl(feedSourceRepository);
	}

	@Test
	public void shouldGetAllFeedSources() {
		// given
		FeedSource feedSource = new FeedSource("name", "url");
		List<FeedSource> feedSources = new ArrayList<FeedSource>(Arrays.asList(feedSource));
		feedSourceRepository.save(feedSources);

		// when
		List<FeedSource> expectedFeedSources = feedSourceService.getAllFeedSources();

		// then
		assertThat(expectedFeedSources, is(feedSources));
	}

}
