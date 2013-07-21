package com.bunker.rffz.web;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import static com.jayway.restassured.config.DecoderConfig.decoderConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.bunker.rffz.domain.ranking.Ranking;
import org.apache.http.HttpHeaders;
import org.junit.Ignore;
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
		RankingList dbRankings = rankingService.getNationalLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/national";

		// when we request a xml response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_XML).config(newConfig().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList restRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
		assertThat(statusCode, is(200));
        assertThat(dbRankings.getRankings().size(), is(restRankings.getRankings().size()));
        //
        for (int i = 0; i < restRankings.getRankings().size(); i++) {
            Ranking dbRanking = dbRankings.getRankings().get(i);
            Ranking restRanking = restRankings.getRankings().get(i);

            assertThat(dbRanking.getRank(), is(restRanking.getRank()));
            assertThat(dbRanking.getTeam(), is(restRanking.getTeam()));
            assertThat(dbRanking.getPoints(), is(restRanking.getPoints()));
            assertThat(dbRanking.getGamesPlayed(), is(restRanking.getGamesPlayed()));
        }
	}
	@Test
    @Ignore
	public void shouldGetNationalRankingsAsJson() {
		// given
		RankingList dbRankings = rankingService.getNationalLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/national";

		// when we request a json response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_JSON).config(newConfig().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList restRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
        assertThat(statusCode, is(200));
        assertThat(dbRankings.getRankings().size(), is(restRankings.getRankings().size()));
        //
        for (int i = 0; i < restRankings.getRankings().size(); i++) {
            Ranking dbRanking = dbRankings.getRankings().get(i);
            Ranking restRanking = restRankings.getRankings().get(i);

            assertThat(dbRanking.getRank(), is(restRanking.getRank()));
            assertThat(dbRanking.getTeam(), is(restRanking.getTeam()));
            assertThat(dbRanking.getPoints(), is(restRanking.getPoints()));
            assertThat(dbRanking.getGamesPlayed(), is(restRanking.getGamesPlayed()));
        }
	}
	@Test
	public void shouldGetNationalRankingsAsAtom() {
		// given
		RankingList dbRankings = rankingService.getNationalLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/national";

		// when we request an atom response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_ATOM).config(newConfig().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList restRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
        assertThat(statusCode, is(200));
        assertThat(dbRankings.getRankings().size(), is(restRankings.getRankings().size()));
        //
        for (int i = 0; i < restRankings.getRankings().size(); i++) {
            Ranking dbRanking = dbRankings.getRankings().get(i);
            Ranking restRanking = restRankings.getRankings().get(i);

            assertThat(dbRanking.getRank(), is(restRanking.getRank()));
            assertThat(dbRanking.getTeam(), is(restRanking.getTeam()));
            assertThat(dbRanking.getPoints(), is(restRanking.getPoints()));
            assertThat(dbRanking.getGamesPlayed(), is(restRanking.getGamesPlayed()));
        }
	}
	
	@Test
	public void shouldGetEuropeanRankingsAsXml() {
		// given
		RankingList dbRankings = rankingService.getEuropeanLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/european";

		// when we request a xml response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_XML).config(newConfig().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList restRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
        assertThat(statusCode, is(200));
        assertThat(dbRankings.getRankings().size(), is(restRankings.getRankings().size()));
        //
        for (int i = 0; i < restRankings.getRankings().size(); i++) {
            Ranking dbRanking = dbRankings.getRankings().get(i);
            Ranking restRanking = restRankings.getRankings().get(i);

            assertThat(dbRanking.getRank(), is(restRanking.getRank()));
            assertThat(dbRanking.getTeam(), is(restRanking.getTeam()));
            assertThat(dbRanking.getPoints(), is(restRanking.getPoints()));
            assertThat(dbRanking.getGamesPlayed(), is(restRanking.getGamesPlayed()));
        }
	}
	@Test
    @Ignore
	public void shouldGetEuropeanRankingsAsJson() {
		// given
		RankingList dbRankings = rankingService.getEuropeanLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/european";

		// when we request a json response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_JSON).config(newConfig().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList restRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
        assertThat(statusCode, is(200));
        assertThat(dbRankings.getRankings().size(), is(restRankings.getRankings().size()));
        //
        for (int i = 0; i < restRankings.getRankings().size(); i++) {
            Ranking dbRanking = dbRankings.getRankings().get(i);
            Ranking restRanking = restRankings.getRankings().get(i);

            assertThat(dbRanking.getRank(), is(restRanking.getRank()));
            assertThat(dbRanking.getTeam(), is(restRanking.getTeam()));
            assertThat(dbRanking.getPoints(), is(restRanking.getPoints()));
            assertThat(dbRanking.getGamesPlayed(), is(restRanking.getGamesPlayed()));
        }
	}
	@Test
	public void shouldGetEuropeanRankingsAsAtom() {
		// given
		RankingList dbRankings = rankingService.getEuropeanLeagueRanking();

		String restUrl = REST_RANKINGS_URL + "/european";

		// when we request an atom response
		RequestSpecification requestSpecification = given().header(HttpHeaders.ACCEPT, MIME_ATOM).config(newConfig().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

		int statusCode = requestSpecification.get(restUrl).getStatusCode();
		RankingList restRankings = requestSpecification.get(restUrl).as(RankingList.class);

		// then
        assertThat(statusCode, is(200));
        assertThat(dbRankings.getRankings().size(), is(restRankings.getRankings().size()));
        //
        for (int i = 0; i < restRankings.getRankings().size(); i++) {
            Ranking dbRanking = dbRankings.getRankings().get(i);
            Ranking restRanking = restRankings.getRankings().get(i);

            assertThat(dbRanking.getRank(), is(restRanking.getRank()));
            assertThat(dbRanking.getTeam(), is(restRanking.getTeam()));
            assertThat(dbRanking.getPoints(), is(restRanking.getPoints()));
            assertThat(dbRanking.getGamesPlayed(), is(restRanking.getGamesPlayed()));
        }
	}
	
}
