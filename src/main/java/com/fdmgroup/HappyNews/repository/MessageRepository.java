package com.fdmgroup.HappyNews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByReceiverOrderBySendDateDesc(HappyUser userFromRepository);

	
}