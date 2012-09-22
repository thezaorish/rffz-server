package com.bunker.rffz.service.feedarticle;

import java.util.List;

import com.bunker.rffz.domain.feedarticle.Candidate;

public interface CandidateService {

	/**
	 * Creates the specified {@link Candidate} only if it is 'business' unique<br>
	 * 'Business' uniqueness is based on title and source
	 * @param candidate the {@link Candidate} to be created
	 */
	void createCandidate(Candidate candidate);

	List<Candidate> getUnprocessedCandidates();

	void updateCandidate(Candidate candidate);

}
