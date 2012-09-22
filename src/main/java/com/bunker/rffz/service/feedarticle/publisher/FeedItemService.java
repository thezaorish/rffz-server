package com.bunker.rffz.service.feedarticle.publisher;

import com.bunker.rffz.domain.feedarticle.dto.FeedItemList;

public interface FeedItemService {

	FeedItemList getFeedItemList(int page, int size);

}
