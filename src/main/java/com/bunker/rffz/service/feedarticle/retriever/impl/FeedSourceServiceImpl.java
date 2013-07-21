package com.bunker.rffz.service.feedarticle.retriever.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.dao.feedarticle.FeedSourceDao;
import com.bunker.rffz.domain.feedarticle.FeedSource;
import com.bunker.rffz.service.feedarticle.retriever.FeedSourceService;

@Service("feedSourceService")
public class FeedSourceServiceImpl implements FeedSourceService {

	private FeedSourceDao feedSourceDao;

	@Autowired
	public FeedSourceServiceImpl(FeedSourceDao feedSourceDao) {
		this.feedSourceDao = feedSourceDao;
	}

	@Override
	public List<FeedSource> getAllFeedSources() {
        return feedSourceDao.getAllFeedSources();
	}

}
