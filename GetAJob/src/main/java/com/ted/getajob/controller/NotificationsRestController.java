package com.ted.getajob.controller;

import com.ted.getajob.model.pojo.NotificationPOJO;
import com.ted.getajob.service.FriendNotificationService;
import com.ted.getajob.service.PostNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationsRestController {

    @Autowired
    FriendNotificationService friendNotificationService;

    @Autowired
    PostNotificationService postNotificationService;

    @GetMapping("/api/notifications/amount/{username}")
    @PreAuthorize("hasRole('USER')")
    public int NotificationsAmount(@PathVariable String username) {
        return friendNotificationService.getFriendNotifications(username) + postNotificationService.getPostNotifications(username);
    }

    @GetMapping("/api/friend/notification/{username}")
    @PreAuthorize("hasRole('USER')")
    public List<NotificationPOJO> getFriendRequestsOfUser(@PathVariable String username) {
        return friendNotificationService.getNotificationsByUsername(username);
    }

    @GetMapping("/api/get/comment/notification/{username}")
    @PreAuthorize("hasRole('USER')")
    public List<NotificationPOJO> getCommentNotificationsOfUser(@PathVariable String username) {
        return postNotificationService.getCommentNotifications(username);
    }

    @GetMapping("/api/get/like/notification/{username}")
    @PreAuthorize("hasRole('USER')")
    public List<NotificationPOJO> getLikeNotificationsOfUser(@PathVariable String username) {
        return postNotificationService.getLikeNotifications(username);
    }

    @DeleteMapping("/api/delete/post/notification/{id}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void viewPostNotification(@PathVariable Long id) {
        postNotificationService.viewPostNotification(id);
    }
}
