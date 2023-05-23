package com.example.spring.reddit.clone.mappper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.spring.reddit.clone.dto.SubredditDto;
import com.example.spring.reddit.clone.model.Post;
import com.example.spring.reddit.clone.model.Subreddit;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
	SubredditDto mapSubredditToDto(Subreddit subreddit);
    
    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);

	
}
