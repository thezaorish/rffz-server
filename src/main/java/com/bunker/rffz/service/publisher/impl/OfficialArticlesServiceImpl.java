package com.bunker.rffz.service.publisher.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.dao.publisher.OfficialArticleDao;
import com.bunker.rffz.domain.publisher.OfficialArticle;
import com.bunker.rffz.domain.publisher.OfficialArticleList;
import com.bunker.rffz.service.publisher.OfficialArticleService;

@Service("officialArticleService")
@Transactional
public class OfficialArticlesServiceImpl implements OfficialArticleService {

	private OfficialArticleDao officialArticleDao;
	
	@Autowired
	public OfficialArticlesServiceImpl(OfficialArticleDao officialArticleDao) {
		this.officialArticleDao = officialArticleDao;
	}
	
	@Override
	public OfficialArticleList getOfficialArticlesList(int page, int size) {
		List<OfficialArticle> officialArticles = officialArticleDao.getOfficialArticles(page, size);
		return new OfficialArticleList(officialArticles);
	}

}
