package com.fdmgroup.HappyNews.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.HappyUser;
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	 @Query("SELECT a FROM Article a ORDER BY a.publicationDate DESC")
	  List<Article> findLatestArticles();
	 
	 @Query("SELECT a FROM Article a ORDER BY a.numberOfComments DESC") 
	 List<Article> findTopSixByOrderByNumberOfCommentsDesc(); 

	 Optional<Article> findByTitle(String name);
		List<Article> findByTitleIgnoreCaseContaining(String name);
		List<Article> findByTitleIgnoreCaseStartingWith(String name);
		List<Article> findByTitleIgnoreCaseEndingWith(String name);
		List<Article> findByCategoryIgnoreCase(String category);
		List<Article> findByLocationIgnoreCase(String location);
		List<Article> findByAuthor(HappyUser author);

//		List<Article> sortArticlesByDate(List<Article> findByCategory); 
	}
	

