package com.bunker.rffz.domain.feedarticle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.domain.feedarticle.FeedSource;

@RunWith(value = Parameterized.class)
public class CandidateTest {

	private static final String DESCRIPTION_WITH_IMG_TAG = "<img src='http://assets.sport.ro/assets/sport/2011/09/07/image_galleries/118470/paszkany-socheaza-nici-barca-nu-batea-branestiul_size4.jpg' width='195' height='120' /> Real description";
	private static final String DESCRIPTION_WITH_P_IMG_A_TAG = "" +
	"<p>A fost cel mai tanar portar campion din istoria Columbiei cand, la 20 de ani.</p> " +
	"<p> " +
	"<a href=\"http://core.ad20.net/click?spgid=0&__x1ts=&pub=62&site=4270&section=0&zone=2008&size=0x0&__x1cguid=1482155370\">" +
	"<img src=\"http://core.ad20.net/ad?__x1ns=1&spgid=0&pub=632203491294404&site=rss.prosport&section=0&zone=RSS_feed&size=728x90&__x1cguid=1236058857\" border=\"0\" alt=\"\"/>" +
	"</a>" +
	"</p>";

	private Candidate candidate;

	private String term;

	@Parameters
	public static Collection<String[]> getTestParameters() {
		return Arrays.asList(new String[][] { { "Steaua" }, { "steaua" }, { "stelisti" }, { "steliste" }, { "stelei" }, { "stelişti" }, { "stelist" } });
	}

	public CandidateTest(String term) {
		this.term = term;
	}

	@Before
	public void setUp() {
		FeedSource feedSource = new FeedSource("name", "url");
		candidate = new Candidate("name", DESCRIPTION_WITH_IMG_TAG, new Date(), "link", feedSource);
	}

	@Test
	public void shouldExtractImagePath() {
		// given a candidate with image tag

		// when
		candidate.extractImagePath();

		// then the image tag should be removed from the description field
		assertThat(candidate.getDescription().trim(), is("Real description"));
		// and the image path should be obtained
		assertThat(candidate.getImagePath(), is("http://assets.sport.ro/assets/sport/2011/09/07/image_galleries/118470/paszkany-socheaza-nici-barca-nu-batea-branestiul_size4.jpg"));
	}
	@Test
	public void shouldExtractImagePath2() {
		// given a candidate without image tag
		candidate.setDescription("Real description");

		// when
		candidate.extractImagePath();

		// then the image tag should be removed from the description field
		assertThat(candidate.getDescription().trim(), is("Real description"));
		// and the image path should be obtained
		assertThat(candidate.getImagePath(), nullValue());
	}

	@Test
	public void shouldCleanupDescriptionWithPAndImgAndATags() {
		// given a candidate with image, a and p tags
		candidate.setDescription(DESCRIPTION_WITH_P_IMG_A_TAG);

		// when
		candidate.extractImagePath();
		candidate.cleanUpAnchor();
		candidate.cleanUpTags();

		// then the proper description should be extracted
		assertThat(candidate.getDescription().trim(), is("A fost cel mai tanar portar campion din istoria Columbiei cand, la 20 de ani."));
	}

	@Test
	public void shouldCandidateBeValidWhenNameContainsSteaua() {
		// given a candidate with a valid name
		candidate.setTitle(term);

		// when
		boolean valid = candidate.isSteauaRelated();

		// then the candidate should be valid
		assertThat(valid, is(true));
	}
	@Test
	public void shouldCandidateBeValidWhenDescriptionContainsSteaua() {
		// given candidate with a valid description
		candidate.setDescription(term);

		// when
		boolean valid = candidate.isSteauaRelated();

		// then the candidate should be valid
		assertThat(valid, is(true));
	}

	@Test
	public void shouldCandidateBeInvalidWhenNeitherNameOrDescriptionContainsSteaua() {
		// given

		// when
		boolean valid = candidate.isSteauaRelated();

		// then
		assertThat(valid, is(false));
	}

}
