package com.vbatis.test.mapper;

import com.vbatis.test.entity.Post;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostMapper {

    /**
    * 添加帖子
    **/
    void insertPost(Post post);

    /**
    * 使用用户id查询帖子
    **/
    List<Post> findAllPostByUserIdWithPage(@Param("userId")  Long userId, @Param("limit") Long limit, @Param("offset") Integer offset);

    /**
    * 查询用户帖子数量
    **/
    Long countPostByUserId(@Param("userId") Long userId);

    /**
    * 使用用户名查询用户帖子数量
    **/
    Long countPostByUserName(@Param("username") String username);

}