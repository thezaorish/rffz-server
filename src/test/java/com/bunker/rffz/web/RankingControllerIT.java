package com.bunker.rffz.web;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.apache.http.HttpHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.domain.ranking.dto.RankingList;
import com.bunker.rffz.service.ranking.RankingService;
import com.jayway.restassured.specification.RequestSpecification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/integrationTest-context.xml")
@Transactional
public class RankingControllerIT {

	private static final String MIME_JSON = "application/json";
	private static final String MIME_XML = "application/xml";
	private static final String MIME_ATOM = "application/atom+xml";
	
	private static final String REST_RANKINGS_URL = "http://localhost:8484/ranking/steaua";
	
	@Autowired
	private RankingService rankingService;
	
	@Test
	public void shouldGetNationalRankingsAsXml() {
		// given
		RankingList nationalRankigns = rankingService.getNationalLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/national";

		// when we request a xml response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_XML);

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList retrievedNationalRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedNationalRankings, is(nationalRankigns));
	}
	@Test
	public void shouldGetNationalRankingsAsJson() {
		// given
		RankingList nationalRankigns = rankingService.getNationalLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/national";

		// when we request a json response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_JSON);

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList retrievedNationalRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedNationalRankings, is(nationalRankigns));
	}
	@Test
	public void shouldGetNationalRankingsAsAtom() {
		// given
		RankingList nationalRankigns = rankingService.getNationalLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/national";

		// when we request an atom response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_ATOM);

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList retrievedNationalRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedNationalRankings, is(nationalRankigns));
	}
	
	@Test
	public void shouldGetEuropeanRankingsAsXml() {
		// given
		RankingList nationalRankigns = rankingService.getEuropeanLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/european";

		// when we request a xml response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_XML);

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList retrievedNationalRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedNationalRankings, is(nationalRankigns));
	}
	@Test
	public void shouldGetEuropeanRankingsAsJson() {
		// given
		RankingList nationalRankigns = rankingService.getEuropeanLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/european";

		// when we request a json response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_JSON);

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList retrievedNationalRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedNationalRankings, is(nationalRankigns));
	}
	@Test
	public void shouldGetEuropeanRankingsAsAtom() {
		// given
		RankingList nationalRankigns = rankingService.getEuropeanLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/european";

		// when we request an atom response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_ATOM);

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList retrievedNationalRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
		assertThat(statusCode, is(200));
		assertThat(retrievedNationalRankings, is(nationalRankigns));
	}
	
}
