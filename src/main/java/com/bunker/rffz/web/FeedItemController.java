package com.bunker.rffz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bunker.rffz.domain.feedarticle.dto.FeedItemList;
import com.bunker.rffz.service.feedarticle.publisher.FeedItemService;

@Controller
@RequestMapping(value = "/feed")
public class FeedItemController {

	private FeedItemService feedItemService;

	@Autowired
	public FeedItemController(FeedItemService feedItemService) {
		this.feedItemService = feedItemService;
	}

	@RequestMapping(value = "/steaua", params = { "page", "size" }, method = RequestMethod.GET)
	@ResponseBody
	public FeedItemList getPaginatedFeeds(@RequestParam("page") int page, @RequestParam("size") int size) {
        return feedItemService.getFeedItemList(page, size);
	}

}
