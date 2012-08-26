package com.bunker.rffz.domain.publisher;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "feedItemList")
@XmlAccessorType(XmlAccessType.NONE)
public class FeedItemList {

	@XmlElement
	private List<FeedItem> feedItems;

	public FeedItemList() {
		// INFO needed by JAX-B
	}
	public FeedItemList(List<FeedItem> feedItems) {
		this.feedItems = feedItems;
	}

	public List<FeedItem> getFeedItems() {
		return feedItems;
	}
	public void setFeedItems(List<FeedItem> feedItems) {
		this.feedItems = feedItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedItems == null) ? 0 : feedItems.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedItemList other = (FeedItemList) obj;
		if (feedItems == null) {
			if (other.feedItems != null)
				return false;
		} else if (!feedItems.equals(other.feedItems))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FeedItemList [feedItems=" + feedItems + "]";
	}

}
