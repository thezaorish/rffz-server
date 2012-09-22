package com.bunker.rffz.dao.feedarticle;

import java.util.List;

import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.domain.feedarticle.FeedSource;


public interface CandidateDao {

	void save(Candidate candidate);

	/**
	 * Retrieves all the unprocessed {@link Candidate}s from all the active {@link FeedSource}s
	 * @return
	 */
	List<Candidate> getUnprocessedCandidates();

	/**
	 * Gets the number of {@link Candidate}s with the specified title and source
	 * @param title the title of the {@link Candidate}
	 * @param source the {@link FeedSource} of the {@link Candidate}
	 * @return
	 */
	int countCandidates(String title, FeedSource source);

}
