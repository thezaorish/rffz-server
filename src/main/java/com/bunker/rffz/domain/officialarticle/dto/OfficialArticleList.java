package com.bunker.rffz.domain.officialarticle.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfficialArticleList other = (OfficialArticleList) obj;
		if (officialArticles == null) {
			if (other.officialArticles != null)
				return false;
		} else if (!officialArticles.equals(other.officialArticles))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OfficialArticleList [officialArticles=" + officialArticles + "]";
	}
	
}
