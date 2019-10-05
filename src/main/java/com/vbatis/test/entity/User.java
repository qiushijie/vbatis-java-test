package com.vbatis.test.entity;

import java.util.List;
import lombok.Data;

/*
* 用户
*/
@Data public class User {

	private Long userId;

	private String username;

	private String email;

	private String password;

	private Integer roleId;

	/*
	* 角色
	*/
	private Role role;

	/*
	* 帖子列表
	*/
	private List<Post> posts;

}