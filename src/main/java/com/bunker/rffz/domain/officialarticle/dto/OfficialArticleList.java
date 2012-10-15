package com.bunker.rffz.domain.officialarticle.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@XmlRootElement(name = "officialArticleList")
@XmlAccessorType(XmlAccessType.NONE)
public class OfficialArticleList {

	@XmlElement
	private List<OfficialArticle> officialArticles;

	public OfficialArticleList() {
		// INFO needed by JAX-B
	}
	public OfficialArticleList(List<OfficialArticle> officialArticles) {
		this.officialArticles = officialArticles;
	}
	
	public List<OfficialArticle> getOfficialArticles() {
		return officialArticles;
	}
	public void setOfficialArticles(List<OfficialArticle> officialArticles) {
		this.officialArticles = officialArticles;
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
		return "OfficialArticleList [officialArticles=" + officialArticles + "]";
	}
	
}
