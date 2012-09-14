package com.bunker.rffz.service.publisher;

import com.bunker.rffz.domain.publisher.FeedItemList;

public interface FeedItemService {

	FeedItemList getFeedItemList(int page, int size);

}
