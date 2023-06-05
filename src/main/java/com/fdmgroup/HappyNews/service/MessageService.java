package com.fdmgroup.HappyNews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Message;
import com.fdmgroup.HappyNews.repository.MessageRepository;


@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public void saveMessage(Message message) {
		messageRepository.save(message);
	}

	public List<Message> listAllReceivedMessages(HappyUser userFromDatabase) {
		
		return messageRepository.findByReceiverOrderBySendDateDesc(userFromDatabase);
	}
	
	 
	public void deleteMessage(Integer id) {
		messageRepository.deleteById(id);;
	}
	
}