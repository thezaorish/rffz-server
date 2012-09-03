package com.bunker.rffz.service.retriever.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.domain.retriever.FeedSource;
import com.bunker.rffz.repository.retriever.FeedSourceRepository;
import com.bunker.rffz.service.retriever.FeedSourceService;

@Service("feedSourceService")
public class FeedSourceServiceImpl implements FeedSourceService {

	private FeedSourceRepository feedSourceRepository;

	@Autowired
	public FeedSourceServiceImpl(FeedSourceRepository feedSourceRepository) {
		this.feedSourceRepository = feedSourceRepository;
	}

	@Override
	public List<FeedSource> getAllFeedSources() {
		List<FeedSource> feedSources = feedSourceRepository.findAll();
		return feedSources;
	}

}
