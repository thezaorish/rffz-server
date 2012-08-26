package com.bunker.rffz.dao.retriever;

import java.util.List;

import com.bunker.rffz.domain.retriever.FeedSource;

public interface FeedSourceDao {

	void save(FeedSource feedSource);

	List<FeedSource> getAllFeedSources();

}
