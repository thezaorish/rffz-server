package com.bunker.rffz.service.officialarticle;

import com.bunker.rffz.domain.officialarticle.dto.OfficialArticleList;

public interface OfficialArticleService {

	OfficialArticleList getOfficialArticlesList(int page, int size);
	
}
