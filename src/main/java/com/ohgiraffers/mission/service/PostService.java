package com.ohgiraffers.mission.service;

import com.ohgiraffers.mission.domain.dto.PostDTO;
import com.ohgiraffers.mission.domain.entity.Post;
import com.ohgiraffers.mission.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){

        this.postRepository = postRepository;

    }

    public List<PostDTO> getAllPosts() {

        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PostDTO convertToDTO(Post post) {
        // 엔티티를 DTO로 변환하는 로직을 구현합니다.
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId((int) post.getPostId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        return postDTO;
    }

//    public Post updatePost(int id, Post modifyInfo) {
//
//
//
//        return Post;
//    }
}
