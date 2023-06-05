package com.fdmgroup.HappyNews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.fdmgroup.HappyNews.model.Category;
import com.fdmgroup.HappyNews.model.HappyUser;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	List<Category> findAllCategoriesByUser(HappyUser user);

}
