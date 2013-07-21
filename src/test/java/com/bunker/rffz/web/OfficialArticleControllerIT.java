package com.bunker.rffz.web;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import static com.jayway.restassured.config.DecoderConfig.decoderConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.http.HttpHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.domain.officialarticle.dto.OfficialArticleList;
import com.bunker.rffz.service.officialarticle.OfficialArticleService;
import com.jayway.restassured.specification.RequestSpecification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/integrationTest-context.xml")
@Transactional
public class OfficialArticleControllerIT {

	private static final String MIME_JSON = "application/json";
	private static final String MIME_XML = "application/xml";
	private static final String MIME_ATOM = "application/atom+xml";
	
	private static final String BASE_OFFICIAL_ARTICLE_URL = "http://localhost:8484/official/steaua";

	@Autowired
	private OfficialArticleService officialArticleService;
	
	@Test
	public void shouldGetOfficialArticlesPaginatedAsXml() {
		// given
		OfficialArticleList officialArticleList = officialArticleService.getOfficialArticlesList(1, 5);

		String restUrl = BASE_OFFICIAL_ARTICLE_URL + "?page=1&size=5";

		// when we request a xml response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_XML).config(newConfig().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		OfficialArticleList retrievedOfficialArticleList = requestSpecification.get(restUrl).as(OfficialArticleList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedOfficialArticleList, is(officialArticleList));
	}
	@Test
	public void shouldGetOfficialArticlesPaginatedAsJson() {
		// given
		OfficialArticleList officialArticleList = officialArticleService.getOfficialArticlesList(3, 1);

		String restUrl = BASE_OFFICIAL_ARTICLE_URL + "?page=3&size=1";

		// when we request a json response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_JSON).config(newConfig().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		OfficialArticleList retrievedOfficialArticleList = requestSpecification.get(restUrl).as(OfficialArticleList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedOfficialArticleList, is(officialArticleList));
	}
	@Test
	public void shouldGetOfficialArticlesPaginatedAsAtom() {
		// given
		OfficialArticleList officialArticleList = officialArticleService.getOfficialArticlesList(2, 2);

		String restUrl = BASE_OFFICIAL_ARTICLE_URL + "?page=2&size=2";

		// when we request an atom response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_ATOM).config(newConfig().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		OfficialArticleList retrievedOfficialArticleList = requestSpecification.get(restUrl).as(OfficialArticleList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedOfficialArticleList, is(officialArticleList));
	}
	
}
