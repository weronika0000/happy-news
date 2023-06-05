package com.fdmgroup.HappyNews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

List<Comment> findByArticle (Article article);
//List<Comment> findByParentComment(Comment parentCommnet);
 
	
	
}
