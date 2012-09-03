package com.bunker.rffz.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bunker.rffz.domain.ScheduledTask;
import com.bunker.rffz.domain.ScheduledTask.Name;
import com.bunker.rffz.domain.ScheduledTask_;

public class ScheduledTaskSpecifications {

	public static Specification<ScheduledTask> getByName(final Name name) {
		return new Specification<ScheduledTask>() {
			@Override
			public Predicate toPredicate(Root<ScheduledTask> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.<Name>get(ScheduledTask_.name), name);
			}
		};
	}
	
}
