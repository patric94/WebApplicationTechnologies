package com.ted.getajob.repository;

import com.ted.getajob.model.PostNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostNotificationRepository extends JpaRepository<PostNotification, Long> {
    @Query(value = "select count(*) " +
            "from user me, post p, post_notification pn " +
            "where me.username=?1 and p.user_id=me.id and p.id=pn.post_id",
            nativeQuery = true)
    int getUsersPostNotifications(String username);

    @Query("select postNotifications " +
            "from PostNotification postNotifications, UserModel me, UserModel user, Post post " +
            "where me.username=?1 and post.userModel.id=me.id and post.id=postNotifications.post.id and postNotifications.type=0 and postNotifications.userModel.id=user.id")

    List<PostNotification> getCommenterOnPost(String username);

    @Query("select postNotifications " +
            "from PostNotification postNotifications, UserModel me, UserModel user, Post post " +
            "where me.username=?1 and post.userModel.id=me.id and post.id=postNotifications.post.id and postNotifications.type=1 and postNotifications.userModel.id=user.id")
    List<PostNotification> getInterestedOnPost(String username);
}
