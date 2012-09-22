package com.bunker.rffz.service.job.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.dao.util.ScheduledTaskDao;
import com.bunker.rffz.domain.util.ScheduledTask;
import com.bunker.rffz.domain.util.ScheduledTask.Name;
import com.bunker.rffz.service.job.ScheduledTaskService;

@Service("scheduledTaskService")
public class ScheduledTaskServiceImpl implements ScheduledTaskService {

	private ScheduledTaskDao scheduledTaskDao;

	@Autowired
	public ScheduledTaskServiceImpl(ScheduledTaskDao scheduledTaskDao) {
		this.scheduledTaskDao = scheduledTaskDao;
	}

	@Override
	public boolean isActive(Name name) {
		return scheduledTaskDao.isActive(name);
	}

	@Override
	public Date getLastRun(Name name) {
		ScheduledTask task = scheduledTaskDao.getByName(name);
		return task == null ? null : task.getLastRun();
	}

	@Override
	public void updateLastRun(Name name) {
		ScheduledTask task = scheduledTaskDao.getByName(name);
		task.setLastRun(new Date());
		scheduledTaskDao.save(task);
	}

}
