package com.thezaorish;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.Link.REL_NEXT;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class ArticleController {

	@RequestMapping("/articles")
	public HttpEntity<PagedResources<Resource<Article>>> articles(@RequestParam(required = false, defaultValue = "10") long size, @RequestParam(required = false, defaultValue = "1") long page) {
		long totalElements = 31;

		List<Resource<Article>> articles = new ArrayList<Resource<Article>>();
		articles.add(article1());
		articles.add(article2());

		PagedResources<Resource<Article>> resources =  new PagedResources(articles, new PageMetadata(size, page, totalElements));
		resources.add(linkTo(methodOn(ArticleController.class).articles(10, 1)).withSelfRel());
		resources.add(linkTo(methodOn(ArticleController.class).articles(10, 2)).withRel(REL_NEXT));

		return new ResponseEntity<PagedResources<Resource<Article>>>(resources, OK);
	}

	@RequestMapping("/articles/{url}")
	public HttpEntity<Resource<Article>> article(@PathVariable String url) {
		return new ResponseEntity<Resource<Article>>(article1(), OK);
	}

	private Resource<Article> article1() {
		Article article = new Article();
		article.setTitle("Echipa Stelei se reîntregește înaintea celui de-al doilea cantonament");
		article.setDescription("Alex Chipciu a fost prezent astăzi la Institutul Național de Medicină Sportivă pentru a efectua vizita medicală.\n" +
				"Mijlocașul se va alătura lotului antrenat de Laurențiu Reghecampf pentru cel de-al doilea cantonament din această vară, care va avea loc &amp;#238;n Olanda.\n" +
				"Chipciu a venit singur la testele medicale și nu a discutat cu reprezentanții presei, notează Digi Sport. ...");
		article.setImagePath("http://www9.gsp.ro/usr/thumbs/thumb_290_x_208/2016/06/30/741743-707555-chipciu.jpg");
		article.setPublishDate("29-06-2016-21-00-00");
		article.setSource("www.gsp.ro");
		article.setUrl("http://www.gsp.ro/fotbal/liga-1/echipa-stelei-se-reintregeste-inaintea-celui-de-al-doilea-cantonament-482250.html");

		Link selfRel = linkTo(methodOn(ArticleController.class).article(article.getUrl())).withSelfRel();
		return new Resource<Article>(article, selfRel);
	}

	private Resource<Article> article2() {
		Article article = new Article();
		article.setTitle("A dat Steaua lovitura? &quot;Este cel mai bun transfer pe care l-a făcut în ultimii ani. E cel mai bun mijlocaş din Liga 1!&quot;");
		article.setDescription("Ilie Dumitrescu este de părere că transferul lui Adnan Aganovic este cea mai importantă mutare făcută de Steaua în această vară. Fostul mijlocaş al Viitorului este văzut perechea ideală pentru Pintilii la mijlocul terenului.");
		article.setImagePath("http://storage0.dms.mpinteractiv.ro/media/401/581/7946/15512227/1/6358502-mediafax-foto-alexandru-hojda.jpg");
		article.setPublishDate("29-06-2016-18-54-47");
		article.setSource("www.prosport.ro");
		article.setUrl("http://feedproxy.google.com/~r/prosport/zWZD/~3/2_XjrwYIaqk/a-dat-steaua-lovitura-este-cel-mai-bun-transfer-pe-care-l-a-facut-in-ultimii-ani-e-cel-mai-bun-mijlocas-din-liga-1-15512227");

		Link selfRel = linkTo(methodOn(ArticleController.class).article(article.getUrl())).withSelfRel();
		return new Resource<Article>(article, selfRel);
	}

}
