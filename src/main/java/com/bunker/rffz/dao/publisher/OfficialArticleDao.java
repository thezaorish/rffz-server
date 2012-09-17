package com.bunker.rffz.dao.publisher;

import java.util.List;

import com.bunker.rffz.domain.publisher.OfficialArticle;

public interface OfficialArticleDao {

	void save(OfficialArticle officialArticle);
	
	List<OfficialArticle> getOfficialArticles(int page, int size);
	
}
