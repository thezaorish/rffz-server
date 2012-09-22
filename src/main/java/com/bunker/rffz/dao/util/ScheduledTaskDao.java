package com.bunker.rffz.dao.util;

import com.bunker.rffz.domain.util.ScheduledTask;
import com.bunker.rffz.domain.util.ScheduledTask.Name;

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
