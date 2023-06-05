package com.fdmgroup.HappyNews.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Request {

	@Id
	@GeneratedValue
	@Column(name = "REQUEST_ID")
	private int requestId;

	@ManyToOne
	private HappyUser sender;


	@Column(name = "MESSAGE_TEXT")
	private String messageText;

	private boolean wasRead;

	@Column(name = "SEND_DATE")
	private LocalDateTime sendDate;

	@OneToOne
	private Article article;

	public Request() {
		
	}
	
	public Request(HappyUser sender,String messageText, boolean wasRead, LocalDateTime sendDate,
			Article article) {
		super();
		this.sender = sender;
		this.messageText = messageText;
		this.wasRead = wasRead;
		this.sendDate = sendDate;
		this.article = article;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int request_Id) {
		this.requestId = request_Id;
	}

	public HappyUser getSender() {
		return sender;
	}

	public void setSender(HappyUser sender) {
		this.sender = sender;
	}

	

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public boolean isWasRead() {
		return wasRead;
	}

	public void setWasRead(boolean wasRead) {
		this.wasRead = wasRead;
	}

	public LocalDateTime getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}

	public Article  getArticle() {
		return article;
	}

	public void setArticle(Article  article) {
		this.article = article;
	}

	@Override
	public int hashCode() {
		return Objects.hash(article, messageText,  sendDate, sender, wasRead);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(article, other.article) && Objects.equals(messageText, other.messageText)
				 && Objects.equals(sendDate, other.sendDate)
				&& Objects.equals(sender, other.sender) && wasRead == other.wasRead;
	}

	@Override
	public String toString() {
		return "RequestMessage [request_Id=" + requestId + ", sender=" + sender 
				 + ", messageText=" + messageText + ", wasRead=" + wasRead + ", sendDate=" + sendDate
				+ ", booking=" + article + "]";
	}

}