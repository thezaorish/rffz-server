package com.bunker.rffz.dao.retriever.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bunker.rffz.dao.retriever.FeedSourceDao;
import com.bunker.rffz.domain.retriever.FeedSource;

@Repository("feedSourceDao")
public class HibernateFeedSourceDao extends HibernateDaoSupport implements FeedSourceDao {

	@Autowired
	public HibernateFeedSourceDao(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void save(FeedSource feedSource) {
		this.getHibernateTemplate().saveOrUpdate(feedSource);
	}

	@Override
	public List<FeedSource> getAllFeedSources() {
		return this.getHibernateTemplate().loadAll(FeedSource.class);
	}

}
