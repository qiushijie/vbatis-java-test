package com.vbatis.test.entity;

import java.util.List;
import lombok.Data;

/*
* 用户
*/
@Data public class User {

	/*
	* 用户id
	*/
	private Long userId;

	/*
	* 用户名
	*/
	private String username;

	/*
	* 邮箱
	*/
	private String email;

	/*
	* 密码
	*/
	private String password;

	/*
	* 角色
	*/
	private Role role;

	/*
	* 帖子列表
	*/
	private List<Post> posts;

}