package com.example.spring.reddit.clone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.reddit.clone.model.Post;
import com.example.spring.reddit.clone.model.User;
import com.example.spring.reddit.clone.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

	Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
