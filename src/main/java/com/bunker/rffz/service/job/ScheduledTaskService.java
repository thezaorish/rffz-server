package com.bunker.rffz.service.job;

import java.util.Date;

import com.bunker.rffz.domain.ScheduledTask.Name;

public interface ScheduledTaskService {

	boolean isActive(Name name);

	Date getLastRun(Name name);

	void updateLastRun(Name name);

}
