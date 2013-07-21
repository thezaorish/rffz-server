package com.bunker.rffz.dao.ranking.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bunker.rffz.dao.ranking.RankingDao;
import com.bunker.rffz.domain.ranking.Ranking;
import com.bunker.rffz.domain.ranking.RankingType;

@Repository("rankingDao")
public class HibernateRankingDao extends HibernateDaoSupport implements RankingDao {

	@Autowired
	public HibernateRankingDao(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Ranking> getByType(RankingType type) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Ranking.class);
		criteria.add(Restrictions.eq("type", type));
		criteria.addOrder(Order.asc("rank"));
        return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void save(Ranking ranking) {
		getHibernateTemplate().saveOrUpdate(ranking);
	}
	
}
