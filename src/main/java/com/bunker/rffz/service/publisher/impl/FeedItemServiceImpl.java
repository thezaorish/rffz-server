package com.bunker.rffz.service.publisher.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.domain.analyser.Article;
import com.bunker.rffz.domain.publisher.FeedItem;
import com.bunker.rffz.domain.publisher.FeedItemList;
import com.bunker.rffz.service.analyser.ArticleService;
import com.bunker.rffz.service.publisher.FeedItemService;

@Service("feedItemService")
@Transactional
public class FeedItemServiceImpl implements FeedItemService {

	private ArticleService articleService;

	@Autowired
	public FeedItemServiceImpl(ArticleService articleService) {
		this.articleService = articleService;
	}

	@Override
	public FeedItemList getFeedItemList(int page, int size) {
		List<Article> articles = articleService.getArticles(page, size);
		return generateFeedItems(articles);
	}

	private FeedItemList generateFeedItems(List<Article> articles) {
		List<FeedItem> feedItems = new ArrayList<FeedItem>();
		for (Article article : articles) {
			feedItems.add(new FeedItem(article));
		}
		return new FeedItemList(feedItems);
	}

}
