package com.bunker.rffz.dao;

import com.bunker.rffz.domain.ScheduledTask;
import com.bunker.rffz.domain.ScheduledTask.Name;

public interface ScheduledTaskDao {

	void save(ScheduledTask scheduledTask);

	/**
	 * Retrieves the {@link ScheduledTask} by name
	 * @param name
	 * @return the {@link ScheduledTask} or null
	 */
	ScheduledTask getByName(Name name);

	boolean isActive(Name name);

}
