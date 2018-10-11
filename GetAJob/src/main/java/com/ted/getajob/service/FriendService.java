package com.ted.getajob.service;

import com.ted.getajob.model.FriendNotification;
import com.ted.getajob.model.FriendRequest;
import com.ted.getajob.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    FriendNotificationService friendNotificationService;

    /*
        Below are implemented methods for the FriendService, such as
        send, accept, delete requests etc.
    */
    public void sendFriendRequest(UserModel senderUser, UserModel receiverUser) {
        FriendRequest friendRequest;
        // We do this check to fulfill database's request.
        if (senderUser.getId() < receiverUser.getId()) {
            friendRequest = new FriendRequest(senderUser,receiverUser, 0, senderUser);
        }
        else {
            friendRequest = new FriendRequest(receiverUser, senderUser, 0, senderUser);
        }
        FriendNotification friendNotification = new FriendNotification(receiverUser, friendRequest);
        friendRequestService.addFriendRequest(friendRequest);
        friendNotificationService.addFriendNotification(friendNotification);
    }

    public void acceptFriendRequest(UserModel myUser, UserModel acceptedUser) {
        FriendRequest friendRequest = friendRequestService.findFriendRequestByUsers(myUser, acceptedUser);
        friendNotificationService.deleteByFriendRequest(friendRequest);
        friendRequestService.markAsAccepted(friendRequest);

    }

    public void declineFriendRequest(UserModel myUser, UserModel declinedUser) {
        FriendRequest friendRequest = friendRequestService.findFriendRequestByUsers(myUser, declinedUser);
        friendNotificationService.deleteByFriendRequest(friendRequest);
        friendRequestService.markAsDeclined(friendRequest);
    }

    public int getRelationshipStatus(Long curId, Long otherId) {
        return friendRequestService.getRelationshipStatus(curId, otherId);
    }
}
