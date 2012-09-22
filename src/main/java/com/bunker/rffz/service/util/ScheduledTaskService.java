package com.bunker.rffz.service.util;

import java.util.Date;

import com.bunker.rffz.domain.util.ScheduledTask.Name;

public interface ScheduledTaskService {

	boolean isActive(Name name);

	Date getLastRun(Name name);

	void updateLastRun(Name name);

}
