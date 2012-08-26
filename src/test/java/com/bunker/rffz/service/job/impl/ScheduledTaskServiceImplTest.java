package com.bunker.rffz.service.job.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.dao.ScheduledTaskDao;
import com.bunker.rffz.domain.ScheduledTask;
import com.bunker.rffz.domain.ScheduledTask.Name;
import com.bunker.rffz.service.job.ScheduledTaskService;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledTaskServiceImplTest {

	private ScheduledTaskService scheduledTaskService;

	@Mock
	private ScheduledTaskDao scheduledTaskDao;

	private Name name;

	@Before
	public void setUp() {
		scheduledTaskService = new ScheduledTaskServiceImpl(scheduledTaskDao);
		name = Name.CandidatesRetrievalJob;
	}
	
	@Test
	public void shouldFindTaskActive() {
		// given the job is active
		given(scheduledTaskDao.isActive(name)).willReturn(true);

		// when
		boolean active = scheduledTaskService.isActive(name);

		// then
		assertThat(active, is(true));
	}
	@Test
	public void shouldFindTaskDisabled() {
		// given the job is NOT active
		given(scheduledTaskDao.isActive(name)).willReturn(false);

		// when
		boolean active = scheduledTaskService.isActive(name);

		// then
		assertThat(active, is(false));
	}

	@Test
	public void shouldGetLastRunWhenExistingTask() {
		// given an existing task
		ScheduledTask task = new ScheduledTask(name, true);
		Date lastRun = new Date(1000);
		task.setLastRun(lastRun);
		given(scheduledTaskDao.getByName(name)).willReturn(task);

		// when
		Date retrievedLastRun = scheduledTaskService.getLastRun(name);
		
		// then
		assertThat(retrievedLastRun, is(lastRun));
	}
	@Test
	public void shouldGetLastRunWhenNoExistingTask() {
		// given an existing task
		given(scheduledTaskDao.getByName(name)).willReturn(null);

		// when
		Date retrievedLastRun = scheduledTaskService.getLastRun(name);

		// then
		assertThat(retrievedLastRun, nullValue());
	}

	@Test
	public void shouldUpdateLastRun() {
		// given an existing task
		ScheduledTask task = new ScheduledTask(name, true);
		Date lastRun = new Date(1000);
		task.setLastRun(lastRun);
		given(scheduledTaskDao.getByName(name)).willReturn(task);

		// when
		scheduledTaskService.updateLastRun(name);

		// then
		assertThat(task.getLastRun().after(lastRun), is(true));
		verify(scheduledTaskDao).save(task);
	}

}
