package com.bunker.rffz.service.publisher;

import com.bunker.rffz.domain.publisher.OfficialArticleList;

public interface OfficialArticleService {

	OfficialArticleList getOfficialArticlesList(int page, int size);
	
}
