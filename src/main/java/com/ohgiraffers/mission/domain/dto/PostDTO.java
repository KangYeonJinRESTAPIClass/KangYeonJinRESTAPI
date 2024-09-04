package com.ohgiraffers.mission.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDTO {

    private  int postId;
    private  String title;
    private String content;


}
