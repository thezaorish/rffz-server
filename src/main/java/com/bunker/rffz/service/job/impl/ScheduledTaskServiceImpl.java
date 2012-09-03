package com.bunker.rffz.service.job.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.domain.ScheduledTask;
import com.bunker.rffz.domain.ScheduledTask.Name;
import com.bunker.rffz.repository.ScheduledTaskRespository;
import com.bunker.rffz.repository.ScheduledTaskSpecifications;
import com.bunker.rffz.service.job.ScheduledTaskService;

@Service("scheduledTaskService")
public class ScheduledTaskServiceImpl implements ScheduledTaskService {

	private ScheduledTaskRespository scheduledTaskRespository;

	@Autowired
	public ScheduledTaskServiceImpl(ScheduledTaskRespository scheduledTaskRespository) {
		this.scheduledTaskRespository = scheduledTaskRespository;
	}

	@Override
	public boolean isActive(Name name) {
		ScheduledTask task = scheduledTaskRespository.findOne(ScheduledTaskSpecifications.getByName(name)); 
		return task == null ? false : task.isActive();
	}

	@Override
	public Date getLastRun(Name name) {
		ScheduledTask task = scheduledTaskRespository.findOne(ScheduledTaskSpecifications.getByName(name)); 
		return task == null ? null : task.getLastRun();
	}

	@Override
	public void updateLastRun(Name name) {
		ScheduledTask task = scheduledTaskRespository.findOne(ScheduledTaskSpecifications.getByName(name)); 
		task.setLastRun(new Date());
		scheduledTaskRespository.save(task);
	}

}
