package com.bunker.rffz.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.bunker.rffz.domain.ScheduledTask.Name;

@StaticMetamodel(ScheduledTask.class)
public class ScheduledTask_ {

	public static volatile SingularAttribute<ScheduledTask, Name> name;
	
}
