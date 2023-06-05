package com.fdmgroup.HappyNews.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Message;
import com.fdmgroup.HappyNews.repository.MessageRepository;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MessageServiceTest {
 
	@InjectMocks
	MessageService service;

	@MockBean
	MessageRepository mockRepository;
	
	@Mock
	Message mockMessage;
	
	@Mock 
	HappyUser mockUser;
	
	@Test
	public void test_saveMessageMethodOfMessageService_callsSaveMethodOfMessageRepository() {
		
		service.saveMessage(mockMessage);
		
		verify(mockRepository, times(1)).save(mockMessage);
	}
	
	@Test 
	public void test_listAllReceivedMessagesMethodOfMessageService_callsSaveofMessageRepository() {
		
		service.listAllReceivedMessages(mockUser);
		
		verify(mockRepository, times(1)).findByReceiverOrderBySendDateDesc(mockUser);
	}
	
	@Test
	public void test_deleteMessageMethodOfMessageService_callsSaveMethodOfMessageRepository() {
		
		service.deleteMessage(mockMessage.getMessageId());
		
		verify(mockRepository, times(1)).deleteById(mockUser.getId());
	}
}
	