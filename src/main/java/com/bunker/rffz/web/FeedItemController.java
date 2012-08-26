package com.bunker.rffz.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bunker.rffz.domain.publisher.FeedItemList;
import com.bunker.rffz.service.publisher.FeedItemService;

@Controller
@RequestMapping(value = "/feed")
public class FeedItemController {

	private FeedItemService feedItemService;

	@Autowired
	public FeedItemController(FeedItemService feedItemService) {
		this.feedItemService = feedItemService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/steaua", params = { "page", "size" }, method = RequestMethod.GET)
	@ResponseBody
	public FeedItemList getPaginatedFeeds(@RequestParam("page") int page, @RequestParam("size") int size) {
		FeedItemList items = feedItemService.getFeedItemList(page, size);
		return items;
	}

	@RequestMapping(value = "/steaua/{date}", method = RequestMethod.GET)
	@ResponseBody
	public FeedItemList getNewestFeeds(@PathVariable("date") Date lastSyncDate) {
		FeedItemList items = feedItemService.getFeedItemListNewerThan(lastSyncDate);
		return items;
	}

}
