package com.vbatis.test;

import com.vbatis.test.entity.Post;
import com.vbatis.test.entity.PostTag;
import com.vbatis.test.entity.Role;
import com.vbatis.test.entity.User;
import com.vbatis.test.mapper.PostMapper;
import com.vbatis.test.mapper.PostTagMapper;
import com.vbatis.test.mapper.RoleMapper;
import com.vbatis.test.mapper.UserMapper;
import lombok.val;
import lombok.var;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.vbatis.test.mapper")
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostTagMapper postTagMapper;

    @Test
    public void testInsertUser() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        user.setEmail(RandomStringUtils.randomNumeric(8));
        user.setPassword(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        val result = userMapper.findUserById(user.getUserId());
        System.out.println(result);
    }

    @Test
    public void testInsertUserAndRole() {
        val role = new Role();
        role.setName(RandomStringUtils.randomNumeric(8));
        roleMapper.insertRole(role);

        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        user.setRole(role);
        userMapper.insertUser(user);

        val result = userMapper.findUserWithRoleById(user.getUserId());
        System.out.println(result);
    }

    @Test
    public void testPage() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        for (var i = 0; i < 10; i ++) {
            val post = new Post();
            post.setTitle(RandomStringUtils.randomNumeric(8));
            post.setContent(RandomStringUtils.randomNumeric(8));
            post.setAuthor(user);
            postMapper.insertPost(post);
        }

        val posts = postMapper.findAllPostByUserIdWithPage(user.getUserId(), 10L, 0);
        System.out.println(posts);
    }

    @Test
    public void testCollection() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        for (var i = 0; i < 5; i ++) {
            val post = new Post();
            post.setTitle(RandomStringUtils.randomNumeric(8));
            post.setContent(RandomStringUtils.randomNumeric(8));
            post.setAuthor(user);
            postMapper.insertPost(post);
        }

        val result = userMapper.findUserWithPostsById(user.getUserId());
        System.out.println(result);
    }

    @Test
    public void testDeepRef() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        val tag = new PostTag();
        tag.setName(RandomStringUtils.randomNumeric(8));
        postTagMapper.insertTag(tag);

        for (var i = 0; i < 5; i ++) {
            val post = new Post();
            post.setTitle(RandomStringUtils.randomNumeric(8));
            post.setAuthor(user);
            post.setTag(tag);
            postMapper.insertPost(post);
        }

        val result = userMapper.findUserWithPostsAndTagById(user.getUserId());
        System.out.println(result);
    }

    @Test
    public void testCount() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        for (var i = 0; i < 9; i ++) {
            val post = new Post();
            post.setTitle(RandomStringUtils.randomNumeric(8));
            post.setAuthor(user);
            postMapper.insertPost(post);
        }

        val result = postMapper.countPostByUserId(user.getUserId());
        System.out.println(result);
    }

    @Test
    public void testCountRef() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        for (var i = 0; i < 11; i ++) {
            val post = new Post();
            post.setTitle(RandomStringUtils.randomNumeric(8));
            post.setAuthor(user);
            postMapper.insertPost(post);
        }

        val result = postMapper.countPostByUserName(user.getUsername());
        System.out.println(result);

    }

}
