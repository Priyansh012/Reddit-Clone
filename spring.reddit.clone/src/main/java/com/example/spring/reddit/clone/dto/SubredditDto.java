package com.example.spring.reddit.clone.dto;

import java.time.Instant;
import java.util.List;

import com.example.spring.reddit.clone.model.Post;
import com.example.spring.reddit.clone.model.Subreddit;
import com.example.spring.reddit.clone.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SubredditDto {
	private Long id;
	private String name;
	private String description;
	private Integer numberOfPosts;
}
