package com.bunker.rffz.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.dao.util.ScheduledTaskDao;
import com.bunker.rffz.domain.util.ScheduledTask;
import com.bunker.rffz.domain.util.ScheduledTask.Name;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/unitTest-context.xml")
@Transactional
public class HibernateScheduledTaskDaoTest {

	@Autowired
	private ScheduledTaskDao scheduledTaskDao;

	@Test
	public void shouldFindTaskAsActive() {
		// given a task is active
		ScheduledTask scheduledTask = new ScheduledTask(Name.CandidatesRetrievalJob, true);
		scheduledTaskDao.save(scheduledTask);

		// when
		boolean active = scheduledTaskDao.isActive(Name.CandidatesRetrievalJob);

		// then
		assertThat(active, is(true));
	}
	@Test
	public void shouldFindTaskAsNotActive() {
		// given a task is NOT active
		ScheduledTask scheduledTask = new ScheduledTask(Name.CandidatesRetrievalJob, false);
		scheduledTaskDao.save(scheduledTask);

		// when
		boolean active = scheduledTaskDao.isActive(Name.CandidatesRetrievalJob);

		// then
		assertThat(active, is(false));
	}
	@Test
	public void shouldFindTaskAsNotActive2() {
		// given a task is active
		ScheduledTask scheduledTask = new ScheduledTask(Name.CandidatesRetrievalJob, false);
		scheduledTaskDao.save(scheduledTask);

		// when we query for another task
		boolean active = scheduledTaskDao.isActive(Name.CandidatesAnalyserJob);

		// then
		assertThat(active, is(false));
	}
	@Test
	public void shouldFindTaskAsNotActiveWhenNoneExist() {
		// given no tasks in db

		// when
		boolean active = scheduledTaskDao.isActive(Name.CandidatesRetrievalJob);

		// then
		assertThat(active, is(false));
	}

	@Test
	public void shouldGetTheTaskByName() {
		// given a task
		ScheduledTask scheduledTask = new ScheduledTask(Name.CandidatesRetrievalJob, true);
		scheduledTaskDao.save(scheduledTask);

		// when
		ScheduledTask retrievedTask = scheduledTaskDao.getByName(Name.CandidatesRetrievalJob);

		// then
		assertThat(retrievedTask, is(scheduledTask));
	}
	@Test
	public void shouldGetTheTaskByNameReturnNull() {
		// given a task
		ScheduledTask scheduledTask = new ScheduledTask(Name.CandidatesRetrievalJob, true);
		scheduledTaskDao.save(scheduledTask);

		// when we query for another
		ScheduledTask retrievedTask = scheduledTaskDao.getByName(Name.CandidatesAnalyserJob);

		// then
		assertThat(retrievedTask, nullValue());
	}
	@Test
	public void shouldGetTheTaskByNameReturnNull2() {
		// given no existing task

		// when
		ScheduledTask retrievedTask = scheduledTaskDao.getByName(Name.CandidatesAnalyserJob);

		// then
		assertThat(retrievedTask, nullValue());
	}

}
