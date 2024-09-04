package com.ohgiraffers.mission.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name="Post")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private  long postId;

    @Column(name="title")
    private  String title;

    @Column(name="content")
    private String content;
}
