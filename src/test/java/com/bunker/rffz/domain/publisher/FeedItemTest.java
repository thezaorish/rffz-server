package com.bunker.rffz.domain.publisher;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

import com.bunker.rffz.domain.feedarticle.Article;
import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.domain.feedarticle.FeedSource;
import com.bunker.rffz.domain.feedarticle.dto.FeedItem;

public class FeedItemTest {

	@Test
	public void shouldConstructConsistentFeedItem() {
		// given an article
		Article article = getTestArticle();

		// when
		FeedItem feedItem = new FeedItem(article);

		// then the created feedItem should be consistent with the article
		assertThat(new FeedItemMatcher(article).matches(feedItem), is(true));
	}
	private Article getTestArticle() {
		FeedSource feedSource = new FeedSource("feedname", "wwww.url.com");
		Candidate candidate = new Candidate("name", "description", new Date(1000), "www.link.com", feedSource);
		candidate.setImagePath("imagePath");
		return new Article(candidate);
	}

}
