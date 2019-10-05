package com.vbatis.test.mapper;

import com.vbatis.test.entity.Role;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

    /**
    * 添加角色
    **/
    void insertRole(Role role);

}