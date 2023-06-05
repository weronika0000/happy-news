package com.fdmgroup.HappyNews.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	@Column(name="comment_ID")
	private int commentId;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	HappyUser commentator;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	Article article;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	Comment parentComment;
	
	//private LocalDateTime publicationTime;
	private String publicationTime;
	
	
	@Column(name="comment_text", length=400)
	private String commentText;

	
	
	
	public Comment() {
		super();
	
	}


	public Comment(HappyUser commentator, Article article, String publicationTime, String commentText) {
		super();
		this.commentator = commentator;
		this.article = article;
		this.publicationTime = publicationTime;
		this.commentText = commentText;
	}

	
	
	

	public Comment(HappyUser commentator, Article article, Comment parentComment, String publicationTime,
			String commentText) {
		super();
		this.commentator = commentator;
		this.article = article;
		this.parentComment = parentComment;
		this.publicationTime = publicationTime;
		this.commentText = commentText;
	}


	public HappyUser getCommentator() {
		return commentator;
	}


	public void setCommentator(HappyUser commentator) {
		this.commentator = commentator;
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public String getPublicationTime() {
		return publicationTime;
	}


	public void setPublicationTime(String publicationTime) {
		this.publicationTime = publicationTime;
	}


	public String getCommentText() {
		return commentText;
	}


	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}


	public int getCommentId() {
		return commentId;
	}


	public Comment getParentComment() {
		return parentComment;
	}


	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentator=" + commentator + ", article=" + article
				+ ", publicationTime=" + publicationTime + ", commentText=" + commentText + "]";
	}


}