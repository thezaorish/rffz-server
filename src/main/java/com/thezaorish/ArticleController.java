package com.thezaorish;

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
		articles.add(article1());
		articles.add(article2());

		Resources<Article> resources = new Resources<Article>(articles);
		resources.add(linkTo(methodOn(ArticleController.class).articles()).withSelfRel());

		return new ResponseEntity<Resources<Article>>(resources, HttpStatus.OK);
	}

	@RequestMapping("/articles/{id}")
	public HttpEntity<Resource<Article>> article(@PathVariable Integer id) {
		Article article = article1();

		Resource<Article> resource = new Resource<Article>(article);
		resource.add(linkTo(methodOn(ArticleController.class).article(id)).withSelfRel());

		return new ResponseEntity<Resource<Article>>(resource, HttpStatus.OK);
	}

	private Article article1() {
		Article article = new Article();
		article.setTitle("Echipa Stelei se reîntregește înaintea celui de-al doilea cantonament");
		article.setDescription("Alex Chipciu a fost prezent astăzi la Institutul Național de Medicină Sportivă pentru a efectua vizita medicală.\n" +
				"Mijlocașul se va alătura lotului antrenat de Laurențiu Reghecampf pentru cel de-al doilea cantonament din această vară, care va avea loc &amp;#238;n Olanda.\n" +
				"Chipciu a venit singur la testele medicale și nu a discutat cu reprezentanții presei, notează Digi Sport. ...");
		article.setImagePath("http://www9.gsp.ro/usr/thumbs/thumb_290_x_208/2016/06/30/741743-707555-chipciu.jpg");
		article.setPublishDate("29-06-2016-21-00-00");
		article.setSource("www.gsp.ro");
		article.setUrl("http://www.gsp.ro/fotbal/liga-1/echipa-stelei-se-reintregeste-inaintea-celui-de-al-doilea-cantonament-482250.html");
		return article;
	}

	private Article article2() {
		Article article = new Article();
		article.setTitle("A dat Steaua lovitura? &quot;Este cel mai bun transfer pe care l-a făcut în ultimii ani. E cel mai bun mijlocaş din Liga 1!&quot;");
		article.setDescription("Ilie Dumitrescu este de părere că transferul lui Adnan Aganovic este cea mai importantă mutare făcută de Steaua în această vară. Fostul mijlocaş al Viitorului este văzut perechea ideală pentru Pintilii la mijlocul terenului.");
		article.setImagePath("http://storage0.dms.mpinteractiv.ro/media/401/581/7946/15512227/1/6358502-mediafax-foto-alexandru-hojda.jpg");
		article.setPublishDate("29-06-2016-18-54-47");
		article.setSource("www.prosport.ro");
		article.setUrl("http://feedproxy.google.com/~r/prosport/zWZD/~3/2_XjrwYIaqk/a-dat-steaua-lovitura-este-cel-mai-bun-transfer-pe-care-l-a-facut-in-ultimii-ani-e-cel-mai-bun-mijlocas-din-liga-1-15512227");
		return article;
	}

}
