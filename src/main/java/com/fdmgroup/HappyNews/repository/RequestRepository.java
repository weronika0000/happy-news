package com.fdmgroup.HappyNews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Request;


@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	List<Request> findBySenderOrderBySendDateDesc(HappyUser userFromRepository);
}