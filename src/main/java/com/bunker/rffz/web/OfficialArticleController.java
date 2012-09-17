package com.bunker.rffz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bunker.rffz.domain.publisher.OfficialArticleList;
import com.bunker.rffz.service.publisher.OfficialArticleService;

@Controller
@RequestMapping(value = "/official")
public class OfficialArticleController {

	private OfficialArticleService officialArticleService;
	
	@Autowired
	public OfficialArticleController(OfficialArticleService officialArticleService) {
		this.officialArticleService = officialArticleService;
	}
	
	@RequestMapping(value = "/steaua", params = { "page", "size" }, method = RequestMethod.GET)
	@ResponseBody
	public OfficialArticleList getPaginatedOfficialArticles(@RequestParam("page") int page, @RequestParam("size") int size) {
		OfficialArticleList officialArticles = officialArticleService.getOfficialArticlesList(page, size);
		return officialArticles;
	}
	
}
