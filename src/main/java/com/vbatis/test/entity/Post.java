package com.vbatis.test.entity;

import java.util.List;
import lombok.Data;

/*
* 帖子
*/
@Data public class Post {

	private Long postId;

	private String title;

	private String content;

	private Long authorId;

	/*
	* 作者
	*/
	private User author;

	/*
	* 标签列表
	*/
	private List<Tag> tags;

}