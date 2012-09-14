package com.bunker.rffz.web;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.http.HttpHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.domain.publisher.FeedItemList;
import com.bunker.rffz.service.publisher.FeedItemService;
import com.jayway.restassured.specification.RequestSpecification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/integrationTest-context.xml")
@Transactional
public class FeedItemControllerIT {

	private static final String MIME_JSON = "application/json";
	private static final String MIME_XML = "application/xml";
	private static final String MIME_ATOM = "application/atom+xml";

	private static final String REST_ALL_ARTICLES_URL = "http://localhost:8484/feed/steaua";

	@Autowired
	private FeedItemService feedItemService;

	@Test
	public void shouldGetFeedItemsPaginatedAsXml() {
		// given
		FeedItemList feedItems = feedItemService.getFeedItemList(1, 5);

		String restUrl = REST_ALL_ARTICLES_URL + "?page=1&size=5";

		// when we request a xml response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_XML);

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		FeedItemList retrievedFeedItemList = requestSpecification.get(restUrl).as(FeedItemList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedFeedItemList, is(feedItems));
	}
	@Test
	public void shouldGetFeedItemsPaginatedAsJson() {
		// given
		FeedItemList feedItems = feedItemService.getFeedItemList(3, 1);

		String restUrl = REST_ALL_ARTICLES_URL + "?page=3&size=1";

		// when we request a xml response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_JSON);

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		FeedItemList retrievedFeedItemList = requestSpecification.get(restUrl).as(FeedItemList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedFeedItemList, is(feedItems));
	}
	@Test
	public void shouldGetFeedItemsPaginatedAsAtom() {
		// given
		FeedItemList feedItems = feedItemService.getFeedItemList(2, 2);

		String restUrl = REST_ALL_ARTICLES_URL + "?page=2&size=2";

		// when we request a xml response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_ATOM);

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		FeedItemList retrievedFeedItemList = requestSpecification.get(restUrl).as(FeedItemList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedFeedItemList, is(feedItems));
	}

}
