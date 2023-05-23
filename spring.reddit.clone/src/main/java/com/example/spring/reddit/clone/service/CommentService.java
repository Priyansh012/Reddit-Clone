package com.example.spring.reddit.clone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring.reddit.clone.dto.CommentDto;
import com.example.spring.reddit.clone.dto.CommentResponse;
import com.example.spring.reddit.clone.exceptions.PostNotFoundException;
import com.example.spring.reddit.clone.mappper.CommentMapper;
import com.example.spring.reddit.clone.model.Comment;
import com.example.spring.reddit.clone.model.NotificationEmail;
import com.example.spring.reddit.clone.model.Post;
import com.example.spring.reddit.clone.model.User;
import com.example.spring.reddit.clone.repository.CommentRepository;
import com.example.spring.reddit.clone.repository.PostRepository;
import com.example.spring.reddit.clone.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
	
    private static final String POST_URL = "";
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final AuthService authService;
	private final CommentMapper commentMapper;
	private final MailContentBuilder mailContentBuilder;
	private final MailService mailService;
	
	public void save(CommentDto commentDto) {
		Post post = postRepository.findById(commentDto.getPostId())
		.orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));
		Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
		commentRepository.save(comment);
		
        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

	public List<CommentDto> getAllCommentsForPost(Long postId) {
		Post post = postRepository.findById(postId)
		.orElseThrow(() -> new PostNotFoundException(postId.toString()));
		return commentRepository.findByPost(post)
				.stream().map(commentMapper:: mapToDto)
				.collect(Collectors.toList());
	}

	public List<CommentDto> getAllCommentsForUser(String userName) {
		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException(userName));
		return commentRepository.findAllByUser(user)
				.stream().map(commentMapper:: mapToDto)
				.collect(Collectors.toList());
		
	}

}
