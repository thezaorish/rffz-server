package com.bunker.rffz.repository.retriever;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.domain.retriever.Candidate_;
import com.bunker.rffz.domain.retriever.FeedSource;

/**
 * Used to create {@link Specification} objects which are used to create JPA criteria queries for {@link Candidate}.
 * @author Victor Balanica
 */
public class CandidateSpecifications {

	public static Specification<Candidate> countByTitleAndFeedSource(final String title, final FeedSource feedSource) {
		return new Specification<Candidate>() {
			@Override
			public Predicate toPredicate(Root<Candidate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Predicate titleEquality = builder.equal(root.<String>get(Candidate_.title), title);
				Predicate feedSourceEquality = builder.equal(root.<FeedSource>get(Candidate_.feedSource), feedSource);
				
				return builder.and(titleEquality, feedSourceEquality);
			}
		};
	}
	
	public static Specification<Candidate> getUnprocessedCandidates() {
		return new Specification<Candidate>() {
			@Override
			public Predicate toPredicate(Root<Candidate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.<Boolean>get(Candidate_.processed), Boolean.FALSE);
			}
		};
	}
	
}
