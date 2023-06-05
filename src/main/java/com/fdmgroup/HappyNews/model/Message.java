package com.fdmgroup.HappyNews.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Message {
	
	@Id
	@GeneratedValue
	@Column(name="MESSAGE_ID")
	private int messageId;

	@ManyToOne
	private HappyUser receiver;
	
	@Column(name="MESSAGE_TEXT")
	private String messageText;
	
	private boolean wasRead;
	
	@Column(name="SEND_DATE")
	private LocalDateTime sendDate;
	
	public Message() {
		super();
	}

	public Message(HappyUser receiver, String messageText, boolean wasRead, LocalDateTime sendDate) {
		super();
		this.receiver = receiver;
		this.messageText = messageText;
		this.wasRead = wasRead;
		this.sendDate = sendDate;
	}

	

	public HappyUser getReceiver() {
		return receiver;
	}

	public void setReceiver(HappyUser receiver) {
		this.receiver = receiver;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public int getMessageId() {
		return messageId;
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

	@Override
	public String toString() {
		return "Message [messageId=" + messageId +  ", receiver=" + receiver + ", messageText="
				+ messageText + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(messageText, receiver);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(messageText, other.messageText) && Objects.equals(receiver, other.receiver)
				;
	}

}
