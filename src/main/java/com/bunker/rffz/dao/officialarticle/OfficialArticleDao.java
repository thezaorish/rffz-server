package com.bunker.rffz.dao.officialarticle;

import java.util.List;

import com.bunker.rffz.domain.officialarticle.dto.OfficialArticle;

public interface OfficialArticleDao {

	void save(OfficialArticle officialArticle);
	
	List<OfficialArticle> getOfficialArticles(int page, int size);
	
}
