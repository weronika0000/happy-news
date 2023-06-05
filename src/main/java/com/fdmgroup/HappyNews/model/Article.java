package com.fdmgroup.HappyNews.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article {

	@Id
	@GeneratedValue
	@Column(name="article_ID")
	private int articleId;
	
	 
	private String title;
	
	@Column(name="article_text", length=1900)
	private String articleText;
	
	private String publicationDate;
	
	private String location;
	
	
	//user or admin?
	@ManyToOne
	HappyUser author;
	
	private boolean status;
	
	private String category;

	private int numberOfComments;
	
	


	public Article() {
		
	}

	
	public Article(String title, String articleText, String publicationDate, String location, HappyUser author, String category) {
		super();
		this.title = title;
		this.articleText = articleText;
		this.publicationDate = publicationDate;
		this.location = location;
		this.author = author;
		this.category = category;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getArticleText() {
		return articleText;
	}


	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}


	public String getPublicationDate() {
		return publicationDate;
	}


	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public HappyUser getAuthor() {
		return author;
	}


	public void setAuthor(HappyUser author) {
		this.author = author;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getArticleId() {
		return articleId;
	}
	public int getNumberOfComments() {
		return numberOfComments;
	}


	public void setNumberOfComments(int numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", articleText=" + articleText
				+ ", publicationDate=" + publicationDate + ", location=" + location + ", author=" + author + ", status="
				+ status + ", category=" + category + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(articleId, articleText, author, category, location, numberOfComments, publicationDate,
				status, title);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return articleId == other.articleId && Objects.equals(articleText, other.articleText)
				&& Objects.equals(author, other.author) && Objects.equals(category, other.category)
				&& Objects.equals(location, other.location) && numberOfComments == other.numberOfComments
				&& Objects.equals(publicationDate, other.publicationDate) && status == other.status
				&& Objects.equals(title, other.title);
	}










	
	
}
