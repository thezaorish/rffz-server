package com.bunker.rffz.dao.feedarticle.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bunker.rffz.dao.feedarticle.CandidateDao;
import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.domain.feedarticle.FeedSource;

@Repository("candidateDao")
public class HibernateCandidateDao extends HibernateDaoSupport implements CandidateDao {

	@Autowired
	public HibernateCandidateDao(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public void save(Candidate candidate) {
		this.getHibernateTemplate().saveOrUpdate(candidate);
	}

	@Override
	public List<Candidate> getUnprocessedCandidates() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Candidate.class);
		criteria.add(Restrictions.eq("processed", false));
		criteria.createCriteria("feedSource", "source");
		criteria.add(Restrictions.eq("source.active", true));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public int countCandidates(String title, FeedSource source) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Candidate.class);
		criteria.add(Restrictions.eq("title", title));
		criteria.createCriteria("feedSource", "source");
		criteria.add(Restrictions.eq("source.name", source.getName()));
		criteria.setProjection(Projections.rowCount());
		List<Long> result = getHibernateTemplate().findByCriteria(criteria);
		return result.get(0).intValue();
	}

}
