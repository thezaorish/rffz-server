package com.bunker.rffz.service.publisher;

import java.util.Date;

import com.bunker.rffz.domain.publisher.FeedItemList;

public interface FeedItemService {

	FeedItemList getFeedItemList(int page, int size);

	FeedItemList getFeedItemListNewerThan(Date lastSyncDate);

	FeedItemList getArticlesWithMaxCreationDate();

}
