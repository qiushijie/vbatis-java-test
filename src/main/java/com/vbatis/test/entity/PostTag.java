package com.vbatis.test.entity;

import java.util.List;
import lombok.Data;

/*
* 帖子评论
*/
@Data public class PostTag {

	/*
	* tag id
	*/
	private Long tagId;

	/*
	* 帖子id
	*/
	private Long postId;

}