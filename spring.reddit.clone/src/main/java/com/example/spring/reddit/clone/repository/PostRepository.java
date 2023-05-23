package com.example.spring.reddit.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.reddit.clone.model.Post;
import com.example.spring.reddit.clone.model.Subreddit;
import com.example.spring.reddit.clone.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);


}
