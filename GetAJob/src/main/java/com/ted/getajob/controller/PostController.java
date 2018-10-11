package com.ted.getajob.controller;

import com.ted.getajob.model.Comment;
import com.ted.getajob.model.Post;
import com.ted.getajob.model.pojo.CommentPOJO;
import com.ted.getajob.model.pojo.PostPOJO;
import com.ted.getajob.model.pojo.UserPOJO;
import com.ted.getajob.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/api/get/post/{id}")
    @PreAuthorize("hasRole('USER')")
    public PostPOJO getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/api/get/posts/{username}")
    @PreAuthorize("hasRole('USER')")
    public Set<PostPOJO> getUserPosts(@PathVariable String username) {
        return postService.getUserPosts(username);
    }

    @GetMapping("/api/get/sorted/posts/{username}")
    @PreAuthorize("hasRole('USER')")
    public PostPOJO[] getSortedPostsOfUser(@PathVariable String username) {
        return postService.getSortedPostsOfUser(username);
    }

    @GetMapping("/api/get/post/comments/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<CommentPOJO> getCommentsOnPost(@PathVariable Long id) {
        return postService.getCommentsOnPost(id);
    }

    @GetMapping("/api/get/like/users/post/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<UserPOJO> getUsersLikesOnPost(@PathVariable Long id) {
        return postService.getUsersLikesOnPost(id);
    }

    @GetMapping("/api/get/amount/like/post/{id}")
    @PreAuthorize("hasRole('USER')")
    public int getAmountOfLikesOnPost(@PathVariable Long id) {
        return postService.getAmountOfLikesOnPost(id);
    }

    @PostMapping("/api/add/post")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void addNewPost(@RequestBody PostPOJO pojo) {
        postService.addNewPost(pojo.getUsername(),pojo.getContent());
    }

    @DeleteMapping("/api/delete/post/{id}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PostMapping("/api/comment/post/{id}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void commentOnPost(@PathVariable Long id, @RequestBody CommentPOJO pojo) {
        postService.commentOnPost(id, pojo.getContent(), pojo.getCommenter());
    }

    @PostMapping("/api/like/post/{id}/{username}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void likePost(@PathVariable Long id, @PathVariable String username) {
        postService.likePost(id, username);
    }

    @GetMapping("/api/like/status/post/{id}/{username}")
    @PreAuthorize("hasRole('USER')")
    public int getLikeStatusOnPost(@PathVariable Long id, @PathVariable String username) {
        return postService.getLikeStatusOnPost(id, username);
    }
}
