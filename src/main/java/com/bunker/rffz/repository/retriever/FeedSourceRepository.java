package com.bunker.rffz.repository.retriever;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bunker.rffz.domain.retriever.FeedSource;

public interface FeedSourceRepository extends JpaRepository<FeedSource, Long>, JpaSpecificationExecutor<FeedSource> {
	
	//

}
