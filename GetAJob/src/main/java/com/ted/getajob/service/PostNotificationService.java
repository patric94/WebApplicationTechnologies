package com.ted.getajob.service;

import com.ted.getajob.model.Post;
import com.ted.getajob.model.PostNotification;
import com.ted.getajob.model.UserModel;
import com.ted.getajob.model.pojo.NotificationPOJO;
import com.ted.getajob.repository.PostNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostNotificationService {
    @Autowired
    private PostNotificationRepository postNotificationRepository;

    @Transactional
    public void addCommentNotification(Post post, UserModel commenter) {
        PostNotification postNotification = new PostNotification(commenter, post, 0);
        postNotificationRepository.save(postNotification);
    }

    @Transactional
    public void addLikeNotification(Post post, UserModel interested) {
        PostNotification postNotification = new PostNotification(interested, post, 1);
        postNotificationRepository.save(postNotification);
    }

    public int getPostNotifications(String username) {
        return postNotificationRepository.getUsersPostNotifications(username);
    }

    public List<NotificationPOJO> getCommentNotifications(String username) {
        List<NotificationPOJO> pojos = new ArrayList<>();
        for (PostNotification postNotification : postNotificationRepository.getCommenterOnPost(username)) {
            pojos.add(postNotification.convertToPOJO());
        }
        return pojos;
    }

    public List<NotificationPOJO> getLikeNotifications(String username) {
        List<NotificationPOJO> pojos = new ArrayList<>();
        for (PostNotification postNotification : postNotificationRepository.getInterestedOnPost(username)) {
            pojos.add(postNotification.convertToPOJO());
        }
        return pojos;
    }

    @Transactional
    public void viewPostNotification(Long id) {
        postNotificationRepository.deleteById(id);
    }
}
