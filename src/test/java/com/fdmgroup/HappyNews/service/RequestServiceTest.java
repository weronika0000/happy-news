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
import com.fdmgroup.HappyNews.model.Request;
import com.fdmgroup.HappyNews.repository.RequestRepository;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RequestServiceTest {

	@InjectMocks
	RequestService service;
	
	@MockBean
	RequestRepository mockRepository;
	
	@Mock
	Request mockRequest;
	
	@Mock
	HappyUser mockUser;
	
	@Test
	public void test_saveRequestMethodOfRequestService_callsSaveMethodOfRequestRepository() {
		
		service.saveRequest(mockRequest);
		
		verify(mockRepository, times(1)).save(mockRequest);
	}
	
	@Test
	public void test_listRequestMethodOfRequestService_callsFindByReceiverOrderBySendDateDesc_ofRequestRepository() {
		
		service.listRequests();
		
		verify(mockRepository, times(1)).findBySenderOrderBySendDateDesc(mockUser);
	}
	
	@Test
	public void test_deleteRequestMethodOfRequestService_callsDeleteByIdMethodOfRequestRepository() {
		
		service.deleteRequest(mockUser.getId());
		
		verify(mockRepository, times(1)).deleteById(mockUser.getId());
	}
	
	@Test
	public void test_getRequestFromDatabaseMethodOfRequestService_callsFindByIdMethodOfRequestRepository() {
		
		service.getRequestFromDatabase(mockUser.getId());
	
		verify(mockRepository, times(1)).findById(mockUser.getId());
	}
	
}