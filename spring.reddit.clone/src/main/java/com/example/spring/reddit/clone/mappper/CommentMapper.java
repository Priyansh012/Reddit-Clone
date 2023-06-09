package com.example.spring.reddit.clone.mappper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.spring.reddit.clone.dto.CommentDto;
import com.example.spring.reddit.clone.model.Comment;
import com.example.spring.reddit.clone.model.Post;
import com.example.spring.reddit.clone.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment map(CommentDto commentDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentDto mapToDto(Comment comment);
}
