package com.bunker.rffz.domain.feedarticle.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public String toString() {
		return "FeedItemList [feedItems=" + feedItems + "]";
	}

}
