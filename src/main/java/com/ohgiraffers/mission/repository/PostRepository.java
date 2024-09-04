package com.ohgiraffers.mission.repository;

import com.ohgiraffers.mission.domain.dto.PostDTO;
import com.ohgiraffers.mission.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

//    List<PostDTO> getAllPosts();

}
