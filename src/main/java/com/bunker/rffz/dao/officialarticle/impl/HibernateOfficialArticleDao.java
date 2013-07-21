package com.bunker.rffz.dao.officialarticle.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bunker.rffz.dao.officialarticle.OfficialArticleDao;
import com.bunker.rffz.domain.officialarticle.dto.OfficialArticle;

@Repository("officialArticleDao")
public class HibernateOfficialArticleDao extends HibernateDaoSupport implements OfficialArticleDao {

	@Autowired
	public HibernateOfficialArticleDao(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void save(OfficialArticle officialArticle) {
		getHibernateTemplate().saveOrUpdate(officialArticle);
	}
	
	@Override
	public List<OfficialArticle> getOfficialArticles(int page, int size) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OfficialArticle.class);
		criteria.addOrder(Order.desc("creationDate"));
        return getHibernateTemplate().findByCriteria(criteria, (page - 1) * size, size);
	}

}
