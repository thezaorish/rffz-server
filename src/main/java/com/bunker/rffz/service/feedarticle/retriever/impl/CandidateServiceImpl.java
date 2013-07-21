package com.bunker.rffz.service.feedarticle.retriever.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.dao.feedarticle.CandidateDao;
import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.service.feedarticle.retriever.CandidateService;

@Service("candidateService")
public class CandidateServiceImpl implements CandidateService {

	private static final Logger logger = Logger.getLogger(CandidateServiceImpl.class);

	private CandidateDao candidateDao;

	@Autowired
	public CandidateServiceImpl(CandidateDao candidateDao) {
		this.candidateDao = candidateDao;
	}

	@Override
	public void createCandidate(Candidate candidate) {
		int count = candidateDao.countCandidates(candidate.getTitle(), candidate.getFeedSource());
		if (count == 0) {
			candidateDao.save(candidate);
			logger.info("createCandidate with id: " + candidate.getId());
		}
	}

	@Override
	public List<Candidate> getUnprocessedCandidates() {
        return candidateDao.getUnprocessedCandidates();
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		candidateDao.save(candidate);
	}

}
