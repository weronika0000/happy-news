package com.fdmgroup.HappyNews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.HappyNews.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
