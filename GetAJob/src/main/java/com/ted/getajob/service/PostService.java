package com.ted.getajob.service;

import com.ted.getajob.model.*;
import com.ted.getajob.model.pojo.CommentPOJO;
import com.ted.getajob.model.pojo.PostPOJO;
import com.ted.getajob.model.pojo.UserPOJO;
import com.ted.getajob.repository.CommentRepository;
import com.ted.getajob.repository.InterestRepository;
import com.ted.getajob.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostNotificationService postNotificationService;

    public Set<PostPOJO> getUserPosts(String username) {
        Set<PostPOJO> retSet = new HashSet<>();
        for (Post post : userService.getUserByUsername(username).getPosts()) {
            retSet.add(post.convertToPOJO());
        }
        return retSet;
    }

    public PostPOJO getPost(Long id) {
        return postRepository.getOne(id).convertToPOJO();
    }

    public List<CommentPOJO> getCommentsOnPost(Long id) {
        List<CommentPOJO> retList = new ArrayList<>();
        for (Comment comment : postRepository.getOne(id).getComments()) {
            retList.add(comment.convertToPOJO());
        }
        return retList;
    }

    public List<UserPOJO> getUsersLikesOnPost(Long id) {
        List<UserPOJO> retList = new ArrayList<>();
        for (Interest interest : postRepository.getOne(id).getInterests()) {
            retList.add(interest.getUserModel().convertToPOJO());
        }
        return retList;
    }

    public int getAmountOfLikesOnPost(Long id) {
        return postRepository.getOne(id).getInterests().size();
    }

    @Transactional
    public void addNewPost(String username, String content) {
        UserModel userModel = userService.getUserByUsername(username);
        Post post = new Post(content, userModel);
        userModel.getPosts().add(post);
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void commentOnPost(Long id, String content, String username) {
        Post post = postRepository.getOne(id);
        UserModel userModel = userService.getUserByUsername(username);
        Comment comment = new Comment(content, post, userModel);
        post.getComments().add(comment);
        userModel.getComments().add(comment);
        postNotificationService.addCommentNotification(post,userModel);
        commentRepository.save(comment);
    }

    @Transactional
    public void likePost(Long id, String username) {
        Post post = postRepository.getOne(id);
        UserModel userModel = userService.getUserByUsername(username);
        Interest interest = new Interest(post, userModel);
        post.getInterests().add(interest);
        userModel.getLikes().add(interest);
        postNotificationService.addLikeNotification(post, userModel);
        interestRepository.save(interest);
    }

    public Set<Post> getLikedPosts(UserModel userModel) {
        Set<Post> likedPosts = new HashSet<>();
        for (Interest interest : userModel.getLikes()) {
            likedPosts.add(interest.getPost());
        }
        return  likedPosts;
    }

    public PostPOJO[] getSortedPostsOfUser(String username) {
        Set<Post> tempSet = new HashSet<>();
        UserModel userModel = userService.getUserByUsername(username);
        tempSet.addAll(userModel.getPosts());
        for (UserModel friend : userModel.getFriends()) {
            tempSet.addAll(friend.getPosts());
            tempSet.addAll(getLikedPosts(friend));
        }
        Post[] array = tempSet.toArray(new Post[0]);
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Post key = array[i];
            int j = i-1;
            while (j >= 0 && array[j].getCreated_at().before(key.getCreated_at())) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
        PostPOJO[] postPOJOS = new PostPOJO[n];
        for (int i = 0; i < n; i++) {
            postPOJOS[i] = array[i].convertToPOJO();
        }
        return postPOJOS;
    }

    public int getLikeStatusOnPost(Long id, String username) {
        UserModel userModel = userService.getUserByUsername(username);
        for (Interest interest : userModel.getLikes()) {
            if (interest.getPost().getId().equals(id)) {
                return 1;
            }
        }
        return 0;
    }
}
