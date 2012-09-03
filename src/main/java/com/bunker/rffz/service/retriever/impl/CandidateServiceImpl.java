package com.bunker.rffz.service.retriever.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.repository.retriever.CandidateRepository;
import com.bunker.rffz.repository.retriever.CandidateSpecifications;
import com.bunker.rffz.service.retriever.CandidateService;

@Service("candidateService")
public class CandidateServiceImpl implements CandidateService {
	
	private static final Logger logger = Logger.getLogger(CandidateServiceImpl.class);

	private CandidateRepository candidateRepository;
	
	@Autowired
	public CandidateServiceImpl(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}
	
	@Override
	public void createCandidate(Candidate candidate) {
		Long count =  candidateRepository.count(CandidateSpecifications.countByTitleAndFeedSource(candidate.getTitle(), candidate.getFeedSource()));
		if (count == 0) {
			candidateRepository.save(candidate);
			logger.info("createCandidate with id: " + candidate.getId());
		}
	}

	@Override
	public List<Candidate> getUnprocessedCandidates() {
		List<Candidate> candidates = candidateRepository.findAll(CandidateSpecifications.getUnprocessedCandidates());
		return candidates;
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
	}

}
