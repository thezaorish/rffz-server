package com.bunker.rffz.service.feedarticle;

import com.bunker.rffz.domain.feedarticle.dto.FeedItemList;

public interface FeedItemService {

	FeedItemList getFeedItemList(int page, int size);

}
