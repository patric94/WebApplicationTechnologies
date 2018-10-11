package com.ted.getajob.service;

import com.ted.getajob.model.FriendNotification;
import com.ted.getajob.model.FriendRequest;
import com.ted.getajob.model.pojo.NotificationPOJO;
import com.ted.getajob.repository.FriendNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendNotificationService {
    @Autowired
    FriendNotificationRepository friendNotificationRepository;

    public void addFriendNotification(FriendNotification friendNotification) {
        friendNotificationRepository.save(friendNotification);
    }

    public FriendNotification findByFriendRequest(FriendRequest friendRequest) {
        return friendNotificationRepository.findByFriendRequest(friendRequest);
    }

    public List<NotificationPOJO> getNotificationsByUsername(String username) {
        List<NotificationPOJO> retList = new ArrayList<>();
        for (FriendNotification friendNotification : friendNotificationRepository.findAllByUserModel_Username(username)){
            retList.add(friendNotification.convertToPOJO());
        }
        return retList;
    }

    public void deleteByFriendRequest(FriendRequest friendRequest) {
        friendNotificationRepository.deleteByFriendRequest(friendRequest);
    }

    public int getFriendNotifications(String username) {
        return friendNotificationRepository.FriendRequestAmount(username);
    }
}
