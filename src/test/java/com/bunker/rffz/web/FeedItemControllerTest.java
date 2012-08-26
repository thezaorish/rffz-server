package com.bunker.rffz.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.domain.publisher.FeedItem;
import com.bunker.rffz.domain.publisher.FeedItemList;
import com.bunker.rffz.service.publisher.FeedItemService;

@RunWith(MockitoJUnitRunner.class)
public class FeedItemControllerTest {

	private FeedItemController controller;

	@Mock
	private FeedItemService feedItemService;

	@Before
	public void setUp() {
		controller = new FeedItemController(feedItemService);
	}

	@Test
	public void sholdGetPaginatedFeedItems() {
		// given some feed items
		int page = 2;
		int size = 5;

		FeedItemList feedItemList = new FeedItemList(new ArrayList<FeedItem>());
		given(feedItemService.getFeedItemList(page, size)).willReturn(feedItemList);

		// when
		FeedItemList returned = controller.getPaginatedFeeds(page, size);

		// then
		verify(feedItemService).getFeedItemList(page, size);
		assertThat(returned, is(feedItemList));
	}

	@Test
	public void shouldGetNewestFeedItems() {
		// given some feed items
		FeedItemList feedItemList = new FeedItemList(new ArrayList<FeedItem>());
		// newer than the reference date
		Date lastSyncDate = new Date();

		given(feedItemService.getFeedItemListNewerThan(lastSyncDate)).willReturn(feedItemList);

		// when
		FeedItemList returned = controller.getNewestFeeds(lastSyncDate);

		// then
		verify(feedItemService).getFeedItemListNewerThan(lastSyncDate);
		assertThat(returned, is(feedItemList));
	}

}
