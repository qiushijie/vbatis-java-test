package com.vbatis.test.mapper;

import com.vbatis.test.entity.PostTag;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostTagMapper {

    /**
    * 添加标签
    **/
    void insertTag(PostTag postTag);

}