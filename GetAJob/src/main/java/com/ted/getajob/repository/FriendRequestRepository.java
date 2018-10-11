package com.ted.getajob.repository;

import com.ted.getajob.model.FriendRequest;
import com.ted.getajob.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    FriendRequest findFriendRequestByUserOneEqualsAndUserTwoEquals(UserModel userOne, UserModel userTwo);

    FriendRequest findByUserOne_IdAndUserTwo_Id(Long userOne, Long userTwo);
}
