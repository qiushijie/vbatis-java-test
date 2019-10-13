package com.vbatis.test.mapper;

import com.vbatis.test.entity.Tag;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagMapper {

    /**
    * 插入标签
    **/
    void insertTag(Tag tag);

}