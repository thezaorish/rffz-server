package com.bunker.rffz.dao.feedarticle;

import java.util.List;

import com.bunker.rffz.domain.feedarticle.FeedSource;


public interface FeedSourceDao {

	void save(FeedSource feedSource);

	List<FeedSource> getAllFeedSources();

}
