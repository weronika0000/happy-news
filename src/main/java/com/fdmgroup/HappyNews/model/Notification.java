package com.fdmgroup.HappyNews.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Notification {
	
	@Id
	@GeneratedValue
	@Column(name="notification_ID")
	private int notificationId;
	
	@ManyToOne
	Article article;
	
	@ManyToOne
	private HappyUser receiver;
	
	private LocalDateTime notificationDate;
	
	private String notificationText;
	
	
	public Notification() {
		super();	
	}


	public Notification(Article article, HappyUser receiver, LocalDateTime notificationDate, String notificationText) {
		super();
		this.article = article;
		this.receiver = receiver;
		this.notificationDate = notificationDate;
		this.notificationText = notificationText;
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public HappyUser getReceiver() {
		return receiver;
	}


	public void setReceiver(HappyUser receiver) {
		this.receiver = receiver;
	}


	public LocalDateTime getNotificationDate() {
		return notificationDate;
	}


	public void setNotificationDate(LocalDateTime notificationDate) {
		this.notificationDate = notificationDate;
	}


	public String getNotificationText() {
		return notificationText;
	}


	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}


	public int getNotificationId() {
		return notificationId;
	}


	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", article=" + article + ", receiver=" + receiver
				+ ", notificationDate=" + notificationDate + ", notificationText=" + notificationText + "]";
	}
	
	

	
	
	
	
	
	
	
}
