package com.vbatis.test;

import com.vbatis.test.entity.*;
import com.vbatis.test.mapper.*;
import lombok.val;
import lombok.var;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
    private TagMapper tagMapper;

    @Test
    public void testInsertUser() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        user.setEmail(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        val result = userMapper.findUserById(user.getUserId());

        Assert.assertEquals(user.getUsername(), result.getUsername());
        Assert.assertEquals(user.getEmail(), result.getEmail());
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

        Assert.assertEquals(role.getName(), result.getRole().getName());
    }

    @Test
    public void testPage() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        List<Post> posts = new ArrayList<>();

        for (var i = 0; i < 10; i ++) {
            val post = new Post();
            post.setTitle(RandomStringUtils.randomNumeric(8));
            post.setContent(RandomStringUtils.randomNumeric(8));
            post.setAuthor(user);
            postMapper.insertPost(post);
            posts.add(post);
        }

        val result = postMapper.findAllPostByUserIdWithPage(user.getUserId(), 10L, 0);
        Assert.assertEquals(10, result.size());

        for (val post : result) {
            Assert.assertTrue(posts.stream().anyMatch(_post -> _post.getPostId().equals(post.getPostId())));
        }
    }

    @Test
    public void testCollection() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        List<Post> posts = new ArrayList<>();

        for (var i = 0; i < 5; i ++) {
            val post = new Post();
            post.setTitle(RandomStringUtils.randomNumeric(8));
            post.setContent(RandomStringUtils.randomNumeric(8));
            post.setAuthor(user);
            postMapper.insertPost(post);
            posts.add(post);
        }

        val result = userMapper.findUserWithPostsById(user.getUserId());

        Assert.assertEquals(5, result.getPosts().size());
        for (val post : result.getPosts()) {
            Assert.assertTrue(posts.stream().anyMatch(_post -> _post.getPostId().equals(post.getPostId())));
        }
    }

    @Test
    public void testDeepRef() {
        val user = new User();
        user.setUsername(RandomStringUtils.randomNumeric(8));
        userMapper.insertUser(user);

        for (var i = 0; i < 5; i ++) {
            val post = new Post();
            post.setTitle(RandomStringUtils.randomNumeric(8));
            post.setAuthor(user);
            postMapper.insertPost(post);
        }

        val result = userMapper.findUserAndPostsAndAuthor(user.getUserId());
        for (val post : result.getPosts()) {
            Assert.assertEquals(user.getUsername(), post.getAuthor().getUsername());
        }
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

        long result = postMapper.countPostByUserId(user.getUserId());
        Assert.assertEquals(9, result);
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

        long result = postMapper.countPostByUserName(user.getUsername());
        Assert.assertEquals(11, result);
    }

    @Test
    public void testManyToMany() {
        List<Tag> tags = new ArrayList<>();
        for (var i = 0; i < 5; i ++) {
            val tag = new Tag();
            tag.setName(RandomStringUtils.randomNumeric(8));
            tagMapper.insertTag(tag);

            tags.add(tag);
        }

        val post = new Post();
        post.setTitle(RandomStringUtils.randomNumeric(8));
        postMapper.insertPost(post);

        for (val tag : tags) {
            val postTag = new PostTag();
            postTag.setTagId(tag.getId());
            postTag.setPostId(post.getPostId());
            // 插入标签
            postMapper.insertPostTag(postTag);
        }

        val result = postMapper.findPostAndTags(post.getPostId());
        Assert.assertEquals(5, result.getTags().size());

        for (val tag : tags) {
            Assert.assertTrue(result.getTags().stream().anyMatch(_tag -> _tag.getId().equals(tag.getId())));
        }
    }

}
