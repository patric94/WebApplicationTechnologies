package com.ted.getajob.service;

import com.ted.getajob.model.FriendRequest;
import com.ted.getajob.model.UserModel;
import com.ted.getajob.repository.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {
    @Autowired
    FriendRequestRepository friendRequestRepository;

    public void addFriendRequest(FriendRequest friendRequest) {
        friendRequestRepository.save(friendRequest);
    }

    public void markAsAccepted(FriendRequest friendRequest) {
        friendRequest.setStatus(1);
        if (friendRequest.getActiveUser().getId() != friendRequest.getUserOne().getId()) {
            friendRequest.setActiveUser(friendRequest.getUserOne());
        }
        else {
            friendRequest.setActiveUser(friendRequest.getUserTwo());
        }
        friendRequestRepository.save(friendRequest);
    }

    public void markAsDeclined(FriendRequest friendRequest) {
        friendRequestRepository.delete(friendRequest);
    }

    public FriendRequest findFriendRequestByUsers(UserModel userOne, UserModel userTwo) {
        if (userOne.getId() < userTwo.getId()) {
            return friendRequestRepository.findFriendRequestByUserOneEqualsAndUserTwoEquals(userOne, userTwo);
        }
        else {
            return friendRequestRepository.findFriendRequestByUserOneEqualsAndUserTwoEquals(userTwo, userOne);
        }
    }

    public int getRelationshipStatus(Long curId, Long otherId) {
        FriendRequest friendRequest;
        if (curId < otherId) {
            friendRequest = friendRequestRepository.findByUserOne_IdAndUserTwo_Id(curId, otherId);
        }
        else {
            friendRequest = friendRequestRepository.findByUserOne_IdAndUserTwo_Id(otherId, curId);
        }
        return (friendRequest != null) ? friendRequest.getStatus() : 3;
    }
}
