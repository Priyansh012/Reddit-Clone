package com.example.spring.reddit.clone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.reddit.clone.model.Subreddit;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long>{
    Optional<Subreddit> findByName(String subredditName);

}
