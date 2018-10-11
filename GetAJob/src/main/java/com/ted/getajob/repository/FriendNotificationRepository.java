package com.ted.getajob.repository;

import com.ted.getajob.model.FriendNotification;
import com.ted.getajob.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendNotificationRepository extends JpaRepository<FriendNotification, Long> {
//    @Query(value = "select u.username"+
//                  " from user u, user me, friend_notification fn, friend_request fr"+
//                  " where me.username=?1 and fn.user_id=me.id and fn.request_id=fr.id and fr.active_user_id=u.id",
//            nativeQuery = true)
//    List<String> findAllByUserModel_Username(String username);

    List<FriendNotification> findAllByUserModel_Username(String username);

    FriendNotification findByFriendRequest(FriendRequest friendRequest);

    void deleteByFriendRequest(FriendRequest friendRequest);

    @Query(value =  "select count(*) " +
                    "from user me, friend_notification fn " +
                    "where me.username=?1 and fn.user_id=me.id",
            nativeQuery = true)
    int FriendRequestAmount(String username);
}
