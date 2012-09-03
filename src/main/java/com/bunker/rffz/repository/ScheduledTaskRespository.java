package com.bunker.rffz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bunker.rffz.domain.ScheduledTask;

public interface ScheduledTaskRespository extends JpaRepository<ScheduledTask, Long>, JpaSpecificationExecutor<ScheduledTask> {

	//
	
}
