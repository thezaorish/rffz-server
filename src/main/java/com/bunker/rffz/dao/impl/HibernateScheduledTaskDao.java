package com.bunker.rffz.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bunker.rffz.dao.ScheduledTaskDao;
import com.bunker.rffz.domain.ScheduledTask;
import com.bunker.rffz.domain.ScheduledTask.Name;

@Repository("scheduledTaskDao")
public class HibernateScheduledTaskDao extends HibernateDaoSupport implements ScheduledTaskDao {

	@Autowired
	public HibernateScheduledTaskDao(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public void save(ScheduledTask scheduledTask) {
		this.getHibernateTemplate().saveOrUpdate(scheduledTask);
	}

	@Override
	public boolean isActive(Name name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ScheduledTask.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("active", true));
		List<ScheduledTask> scheduledTasks = getHibernateTemplate().findByCriteria(criteria);
		return !scheduledTasks.isEmpty();
	}

	@Override
	public ScheduledTask getByName(Name name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ScheduledTask.class);
		criteria.add(Restrictions.eq("name", name));
		List<ScheduledTask> scheduledTasks = getHibernateTemplate().findByCriteria(criteria);
		if (scheduledTasks.isEmpty()) {
			return null;
		}
		return scheduledTasks.get(0);
	}

}
