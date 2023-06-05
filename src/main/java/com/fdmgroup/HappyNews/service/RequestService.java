package com.fdmgroup.HappyNews.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Request;
import com.fdmgroup.HappyNews.repository.RequestRepository;

@Service
public class RequestService {

	private RequestRepository requestRepository;
	
	@Autowired
	public RequestService(RequestRepository requestRepository) {
		this.requestRepository = requestRepository;
	}

	public void saveRequest(Request request) {
		requestRepository.save(request);
	}
	
	public List<Request> listRequests() {
		
		return requestRepository.findAll();
		
	}
	
	public void deleteRequest(int requestId) {
		
		requestRepository.deleteById(requestId);
	}
	
	public Request getRequestFromDatabase(int requestId) {
		Optional<Request> requestFromDatabase = requestRepository.findById(requestId);

		return requestFromDatabase.orElse(new Request());
	}
	
	
}
