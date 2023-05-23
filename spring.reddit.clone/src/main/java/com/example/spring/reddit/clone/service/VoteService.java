package com.example.spring.reddit.clone.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.reddit.clone.dto.VoteDto;
import com.example.spring.reddit.clone.exceptions.PostNotFoundException;
import com.example.spring.reddit.clone.exceptions.SpringRedditException;
import com.example.spring.reddit.clone.model.Post;
import com.example.spring.reddit.clone.model.Vote;
import static com.example.spring.reddit.clone.model.VoteType.UPVOTE;
import com.example.spring.reddit.clone.repository.PostRepository;
import com.example.spring.reddit.clone.repository.VoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {

	private final VoteRepository voteRepository;
	private final PostRepository postRepository;
	private final AuthService authService;

	@Transactional
	public void vote(VoteDto voteDto) {
		Post post = postRepository.findById(voteDto.getPostId())
				.orElseThrow(() -> new PostNotFoundException("Post not found with id" + voteDto.getPostId()));
		Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
		
		if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
			throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
		}
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);


	}

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }

}
