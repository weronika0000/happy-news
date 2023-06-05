package com.fdmgroup.HappyNews.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.HappyNews.model.HappyUser;

@Repository
public interface HappyUserRepository extends JpaRepository<HappyUser, Long>{
	Optional<HappyUser> findByUsername(String name);
	Optional<HappyUser> findByUsernameIgnoreCase(String name);
	Optional<HappyUser> findByEmail(String email);
} 
