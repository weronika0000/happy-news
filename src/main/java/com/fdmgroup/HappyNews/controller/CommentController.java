package com.fdmgroup.HappyNews.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.CommentRepository;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.CommentService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@Controller
public class CommentController {

	@Autowired
	private MainController mainController;

	@Autowired
	private HappyUserDetailsService happyUserService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	@PostMapping("/addComment")
	public String addComment(ModelMap model, @RequestParam Integer articleId, @RequestParam String commentator,
			@RequestParam String commentText, @RequestParam(required = false) Integer parentCommentId) {
		mainController.returnUserFromCurrentSession(model);
		HappyUser user = happyUserService.findUserByName(commentator);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime publicationTime = LocalDateTime.now();
		String formattedDate = publicationTime.format(formatter);
		Article articleToComment = articleService.findByArticleId(articleId);

//		Comment parentComment = null;
//		if (parentCommentId != null) {
//			parentComment = commentService.findCommentById(parentCommentId);
//		}

		Comment comment = new Comment(user, articleToComment, formattedDate, commentText);
		if (parentCommentId != null) {
	        Comment parentComment = commentService.findCommentById(parentCommentId);
	        comment.setParentComment(parentComment);
	    }

//		comment.setParentComment(parentComment);
		model.addAttribute("article", articleToComment);
		
		articleToComment.setNumberOfComments(articleToComment.getNumberOfComments() + 1);

		commentService.saveComment(comment);
		model.addAttribute("comment", comment);
		List<Comment> listOfCommentsOfArticle = commentService.listOfCommentsForArticle(articleToComment);

		model.addAttribute("listOfCommentsOfArticle", listOfCommentsOfArticle);
//		for(Comment comment2 :listOfCommentsOfArticle ) {
//			System.out.println(comment2);
//		}

		return "article";
	}

	@PostMapping("/addCommentReply")
	public String addCommentReply(ModelMap model, @RequestParam Integer articleId, @RequestParam String commentator,
			@RequestParam String commentText, @RequestParam Integer parentCommentId) {
		mainController.returnUserFromCurrentSession(model);
		HappyUser user = happyUserService.findUserByName(commentator);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime publicationTime = LocalDateTime.now();
		String formattedDate = publicationTime.format(formatter);
		Article article = articleService.findByArticleId(articleId);
		Comment parentComment = commentService.findCommentById(parentCommentId);

		Comment commentReply = new Comment(user, article, parentComment, formattedDate, commentText);
		
		article.setNumberOfComments(article.getNumberOfComments() + 1);
		commentService.saveComment(commentReply);
		model.addAttribute("comment", commentReply);
		List<Comment> listOfCommentsReply = commentService.listOfCommentsForArticle(article);
		
		model.addAttribute("listOfCommentsOfArticle", listOfCommentsReply);
		model.addAttribute("article", article);

		return "article";

	}
	
	
	/*@GetMapping("/deleteComment")
	public String deleteComment(ModelMap model, @RequestParam("commentId") Integer commentId, @RequestParam("articleId")Integer articleId){
		System.out.println(" we are in contriller ===================================================" + commentId);
		mainController.returnUserFromCurrentSession(model);
		commentService.deleteComment(commentId);
		return "redirect:/goToEditArticle/{articleId}";
		
	}*/

}
