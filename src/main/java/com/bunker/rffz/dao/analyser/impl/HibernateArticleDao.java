package com.bunker.rffz.dao.analyser.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bunker.rffz.dao.analyser.ArticleDao;
import com.bunker.rffz.domain.analyser.Article;

@Repository("articleDao")
public class HibernateArticleDao extends HibernateDaoSupport implements ArticleDao {

	@Autowired
	public HibernateArticleDao(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public void save(Article article) {
		this.getHibernateTemplate().saveOrUpdate(article);
	}

	@Override
	public List<Article> getAllArticles() {
		return this.getHibernateTemplate().loadAll(Article.class);
	}

	@Override
	public List<Article> getArticles(int page, int size) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Article.class);
		criteria.createAlias("candidate", "c");
		criteria.addOrder(Order.desc("c.publishDate"));
		List<Article> articles = getHibernateTemplate().findByCriteria(criteria, (page - 1) * size, size);
		return articles;
	}

	@Override
	public List<Article> getArticlesNewerThan(Date date) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Article.class);
		criteria.add(Restrictions.ge("creationDate", date));
		List<Article> articles = getHibernateTemplate().findByCriteria(criteria);
		return articles;
	}

	@Override
	public List<Article> getArticlesWithMaxCreationDate() {
		DetachedCriteria maxDateCriteria = DetachedCriteria.forClass(Article.class);
		maxDateCriteria.setProjection(Projections.max("creationDate"));
		List<Date> result = getHibernateTemplate().findByCriteria(maxDateCriteria);
		Date maxDate = result.get(0);

		return getArticlesNewerThan(maxDate);
	}

}
