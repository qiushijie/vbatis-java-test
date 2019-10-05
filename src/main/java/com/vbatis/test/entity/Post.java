package com.vbatis.test.entity;

import lombok.Data;

@Data public class Post {

    private Long postId;

    private String title;

    private String content;

    private User author;

    private PostTag tag;

}
