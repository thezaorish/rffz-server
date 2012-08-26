package com.bunker.rffz.service.retriever.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.dao.retriever.FeedSourceDao;
import com.bunker.rffz.domain.retriever.FeedSource;
import com.bunker.rffz.service.retriever.FeedSourceService;

@Service("feedSourceService")
public class FeedSourceServiceImpl implements FeedSourceService {

	private FeedSourceDao feedSourceDao;

	@Autowired
	public FeedSourceServiceImpl(FeedSourceDao feedSourceDao) {
		this.feedSourceDao = feedSourceDao;
	}

	@Override
	public List<FeedSource> getAllFeedSources() {
		List<FeedSource> feedSources = feedSourceDao.getAllFeedSources();
		return feedSources;
	}

}
