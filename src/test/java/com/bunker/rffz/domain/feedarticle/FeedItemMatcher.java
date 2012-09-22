package com.bunker.rffz.domain.feedarticle;

import java.text.SimpleDateFormat;

import org.mockito.ArgumentMatcher;

import com.bunker.rffz.domain.feedarticle.Article;
import com.bunker.rffz.domain.feedarticle.dto.FeedItem;

public class FeedItemMatcher extends ArgumentMatcher<FeedItem> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

	private Article article;

	public FeedItemMatcher(Article article) {
		this.article = article;
	}

	@Override
	public boolean matches(Object argument) {
		FeedItem feedItem = (FeedItem) argument;

		if (!feedItem.getTitle().equals(article.getCandidate().getTitle())) {
			return false;
		}
		if (!feedItem.getDescription().equals(article.getCandidate().getDescription())) {
			return false;
		}
		if (!feedItem.getImagePath().equals(article.getCandidate().getImagePath())) {
			return false;
		}
		if (!feedItem.getPublishDate().equals(dateFormat.format(article.getCandidate().getPublishDate()))) {
			return false;
		}
		if (!feedItem.getCreationDate().equals(dateFormat.format(article.getCreationDate()))) {
			return false;
		}
		if (!feedItem.getUrl().equals(article.getCandidate().getUrl())) {
			return false;
		}
		if (!feedItem.getSource().equals(article.getCandidate().getFeedSource().getName())) {
			return false;
		}

		return true;
	}

}
