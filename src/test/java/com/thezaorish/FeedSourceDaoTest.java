package com.thezaorish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by zaorish on 26/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceJpaConfig.class}, loader = AnnotationConfigContextLoader.class)
public class FeedSourceDaoTest {

	@Autowired
	private FeedSourceDao feedSourceDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void shouldSaveFeedSource() {
		// given
		assertThat(jdbcTemplate.queryForObject("select count(*) from feed_source", Integer.class), is(0));

		// when
		feedSourceDao.save(new FeedSource("name", "url"));

		// then
		assertThat(jdbcTemplate.queryForObject("select count(*) from feed_source", Integer.class), is(1));
	}

}
