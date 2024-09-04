package com.ohgiraffers.mission.controller;

import com.ohgiraffers.mission.common.ResponseMessage;
import com.ohgiraffers.mission.domain.dto.PostDTO;
import com.ohgiraffers.mission.domain.entity.Post;
import com.ohgiraffers.mission.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Operation(summary = "회원번호로 회원 조회", description = "회원번호를 통회 회원을 조회한다",
            parameters = {
                    @Parameter(
                            name="postNo",
                            description = "사용자 화면에서 넘어오는 post의 pk"
                    )
            })
    @GetMapping("/posts/{postNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int postNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType(
                        "application",
                        "json",
                        Charset.forName("UTF-8")
                )
        );

        List<PostDTO> posts = postService.getAllPosts();

        PostDTO foundPost = posts.stream()
                .filter(post -> post.getPostId() == postNo).toList().get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("post", foundPost);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "조회성공", responseMap));
    }

    //수정
    @PutMapping("/posts/{postNo}")
    public ResponseEntity<?> modifyPost(@PathVariable int postNo, @RequestBody PostDTO modifyInfo){

        // 게시글 수정 서비스 메서드 호출
        postService.updatePost(postNo, modifyInfo);

        // 수정 후 새로운 리소스 위치를 나타내는 URI 반환
        return ResponseEntity.noContent().build();

    }


}
