package com.vbatis.test.mapper;

import com.vbatis.test.entity.User;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
    * 添加用户和角色
    **/
    void insertUser(User user);

    /**
    * 查询单个用户
    **/
    User findUserById(@Param("id") Long id);

    /**
    * 查询单个用户和角色
    **/
    User findUserWithRoleById(@Param("id") Long id);

    /**
    * 查询单个用户和帖子
    **/
    User findUserWithPostsById(@Param("id") Long id);

    /**
    * 深查询测试
    **/
    User findUserAndPostsAndAuthor(@Param("id") Long id);

}