package com.ohgiraffers.mission.controller;

import com.ohgiraffers.mission.common.ResponseMessage;
import com.ohgiraffers.mission.domain.dto.PostDTO;
import com.ohgiraffers.mission.domain.entity.Post;
import com.ohgiraffers.mission.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Tag(name="Spring boot Swagger 연동 API(USER 기능)")
@Slf4j
@RestController
@RequestMapping("/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    //http get요청을 처리하는 메소드
    //    http://localhost:8080/swagger/posts
    //    @Operation(summary = "전체 회원 조회", description=" 우리 사이트의 전체 회원목록 조회")
    @GetMapping("/posts")
    public ResponseEntity<ResponseMessage> findAllPosts() {

        //http 응답 헤더를 설정함
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType(
                        "application",
                        "json",
                        Charset.forName("UTF-8")
                )
        );

        //서비스에서 게시글 목록을 가져옴
        List<PostDTO> posts = postService.getAllPosts();

        //응답에 포함할 데이터를 준비함
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("posts", posts);

        //응답메세지를 생성함
        ResponseMessage responseMessage = new ResponseMessage(
                200,
                "조회 성공!",
                responseMap
        );

        //responseEntity를 생성하여 응답을 반환함
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    //등록

    //전체조회

    //단일조회

    //수정

    //삭제

}
