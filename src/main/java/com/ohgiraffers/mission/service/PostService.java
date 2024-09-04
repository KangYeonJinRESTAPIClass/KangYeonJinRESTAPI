package com.ohgiraffers.mission.service;

import com.ohgiraffers.mission.domain.dto.PostDTO;
import com.ohgiraffers.mission.domain.entity.Post;
import com.ohgiraffers.mission.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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


    public void updatePost(int postNo, PostDTO modifyInfo) {

        // postNo에 해당하는 게시글을 찾음
        Optional<Post> postOptional = postRepository.findById(postNo);

        if (postOptional.isPresent()) {
            Post existingPost = postOptional.get();
            // 게시글 내용을 수정함
            existingPost.setTitle(modifyInfo.getTitle());
            existingPost.setContent(modifyInfo.getContent());

            // 수정된 게시글을 저장함
            postRepository.save(existingPost);

        } else {
            // postNo에 해당하는 게시글이 없을 경우 예외를 던질 수도 있음
            throw new EntityNotFoundException("Post not found with ID: " + postNo);
        }

    }

    public Optional<PostDTO> getPostById(int postNo) {

        Optional<Post> postOptional = postRepository.findById(postNo);
        return postOptional.map(this::convertToDTO);
    }

    public void deletePost(int postNo) {

        if (postRepository.existsById(postNo)) {
            postRepository.deleteById(postNo);
        } else {
            throw new EntityNotFoundException("Post not found with ID: " + postNo);
        }

    }
}
