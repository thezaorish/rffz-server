package com.bunker.rffz.domain.retriever;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * A meta model class used to create type safe queries from person information.
 * @author Victor Balanica
 */
@StaticMetamodel(Candidate.class)
public class Candidate_ {

	public static volatile SingularAttribute<Candidate, String> title;
	public static volatile SingularAttribute<Candidate, FeedSource> feedSource; 
	public static volatile SingularAttribute<Candidate, Boolean> processed;
	
}
