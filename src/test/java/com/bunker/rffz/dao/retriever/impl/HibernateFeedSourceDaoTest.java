package com.bunker.rffz.dao.retriever.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.dao.feedarticle.FeedSourceDao;
import com.bunker.rffz.domain.feedarticle.FeedSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/unitTest-context.xml")
@Transactional
public class HibernateFeedSourceDaoTest {

	@Autowired
	private FeedSourceDao feedSourceDao;

	private FeedSource feedSource;

	@Before
	public void setUp() {
		feedSource = new FeedSource("name", "url");
	}

	@Test
	public void shouldSaveFeedSource() {
		// given a configured feed source

		// when
		feedSourceDao.save(feedSource);

		// then only the saved feed source should be in the db
		List<FeedSource> allFeedSources = feedSourceDao.getAllFeedSources();
		assertThat(allFeedSources.size(), is(1));
		assertThat(allFeedSources.get(0), is(feedSource));
	}

}
