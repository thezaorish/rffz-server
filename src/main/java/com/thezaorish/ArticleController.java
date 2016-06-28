package com.thezaorish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by zaorish on 28/06/16.
 */
@RestController
public class ArticleController {

	@RequestMapping("/articles")
	public HttpEntity<Resources<Article>> articles() {
		List<Article> articles = new ArrayList<Article>();
		articles.add(article("some example", "www.google.com"));
		articles.add(article("another example", "www.wikipedia.com"));

		Resources<Article> resources = new Resources<Article>(articles);
		resources.add(linkTo(methodOn(ArticleController.class).articles()).withSelfRel());

		return new ResponseEntity<Resources<Article>>(resources, HttpStatus.OK);
	}

	@RequestMapping("/articles/{id}")
	public HttpEntity<Resource<Article>> article(@PathVariable Integer id) {
		Article article = article("some example", "www.google.com");

		Resource<Article> resource = new Resource<Article>(article);
		resource.add(linkTo(methodOn(ArticleController.class).article(id)).withSelfRel());

		return new ResponseEntity<Resource<Article>>(resource, HttpStatus.OK);
	}

	private Article article(String title, String url) {
		Article article = new Article();
		article.setTitle(title);
		article.setUrl(url);
		return article;
	}

}
