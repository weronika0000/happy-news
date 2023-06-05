package com.fdmgroup.HappyNews.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.repository.CommentRepository;

@Service
public class CommentService {

	CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}
	
	public void saveComment(Comment comment) {
		commentRepository.save(comment);
		
	}
	
	
	public void deleteComment (int commentId) {
		commentRepository.deleteById(commentId);
		
	}
	
	/*public void deleteComment (int commentId) {
		System.out.println("we are in the service====================================================" + commentId);
		Optional<Comment> optinal =commentRepository.findById(commentId);
		if(optinal.isPresent()) {
			List<Comment> listOfChildComments = commentRepository.findByParentComment(optinal.get());
			if(!listOfChildComments.isEmpty() || listOfChildComments.size()>0) {
				for(Comment comment: listOfChildComments) {
					commentRepository.delete(comment);
				}
			}
			
			commentRepository.deleteById(commentId);;
		}
		
		
	}*/
	
	public List <Comment> listOfCommentsForArticle(Article article){
		return commentRepository.findByArticle(article);
		
		
		
	}

	public Comment findCommentById(Integer id) {
	    return commentRepository.findById(id).orElse(null);
	}
	
}
