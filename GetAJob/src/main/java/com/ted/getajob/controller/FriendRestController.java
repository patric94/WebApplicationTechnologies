package com.ted.getajob.controller;

import com.ted.getajob.model.Chat;
import com.ted.getajob.model.UserModel;
import com.ted.getajob.model.pojo.UserPOJO;
import com.ted.getajob.service.ChatService;
import com.ted.getajob.service.FriendService;
import com.ted.getajob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class FriendRestController {

    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @Autowired
    ChatService chatService;

    @PostMapping("/api/friend/request/{sender}/{receiver}")
    @PreAuthorize("hasRole('USER')")
    public void sendFriendRequest(@PathVariable String sender, @PathVariable String receiver) {
        UserModel senderUser, receiverUser;
        senderUser = userService.getUserByUsername(sender);
        receiverUser = userService.getUserByUsername(receiver);

        friendService.sendFriendRequest(senderUser, receiverUser);
    }

    @PutMapping("/api/friend/accept/{myUsername}/{accepted}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void acceptFriendRequest(@PathVariable String myUsername, @PathVariable String accepted) {
        UserModel myUser, acceptedUser;
        myUser = userService.getUserByUsername(myUsername);
        acceptedUser = userService.getUserByUsername(accepted);

        friendService.acceptFriendRequest(myUser, acceptedUser);
        myUser.getFriends().add(acceptedUser);
        acceptedUser.getFriends().add(myUser);
        Chat chat = chatService.createChat(myUser,acceptedUser);
        myUser.getChats().add(chat);
        acceptedUser.getChats().add(chat);
    }

    @DeleteMapping("/api/friend/decline/{myUsername}/{declined}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void declineFriendRequest(@PathVariable String myUsername, @PathVariable String declined) {
        UserModel myUser, declinedUser;
        myUser = userService.getUserByUsername(myUsername);
        declinedUser = userService.getUserByUsername(declined);

        friendService.declineFriendRequest(myUser, declinedUser);
    }

    @GetMapping("/api/friend/list/{username}")
    @PreAuthorize("hasRole('USER')")
    public List<UserPOJO> getFriendList(@PathVariable String username) {
        List<UserPOJO> retList = new ArrayList<>();
        for (UserModel user: userService.getFriendsOfUser(username) ) {
            retList.add(user.convertToPOJO());
        }
        return retList;
    }

    @GetMapping("/api/relationship/status/{current}/{username}")
    @PreAuthorize("hasRole('USER')")
    public int getRelationshipStatus(@PathVariable String current, @PathVariable String username) {
        Long curUser, othUser;
        curUser = userService.getUserByUsername(current).getId();
        othUser = userService.getUserByUsername(username).getId();

        return friendService.getRelationshipStatus(curUser, othUser);
    }
}
