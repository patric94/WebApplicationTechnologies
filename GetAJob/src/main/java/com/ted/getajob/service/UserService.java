package com.ted.getajob.service;

import com.ted.getajob.model.Authority;
import com.ted.getajob.model.AuthorityName;
import com.ted.getajob.model.UserModel;
import com.ted.getajob.model.pojo.UserPOJO;
import com.ted.getajob.repository.AuthorityRepository;
import com.ted.getajob.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        AuthorityName name = AuthorityName.ROLE_USER;
        Authority auth = authorityRepository.findByName(name);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(auth);
        user.setAuthorities(authorities);
        userModelRepository.save(user);
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();
        userModelRepository.findAll().forEach(users::add);
        return users;
    }

    public UserModel getUserByUsername(String username) {
        return userModelRepository.findByUsername(username);
    }

    public Set<UserModel> getFriendsOfUser(String username) {
        return userModelRepository.findByUsername(username).getFriends();
    }

    public void changeEmail(String username, String email) {
        UserModel user = userModelRepository.findByUsername(username);
        user.setEmail(email);
        userModelRepository.save(user);
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        UserModel user = userModelRepository.findByUsername(username);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {

            user.setPassword(passwordEncoder.encode(newPassword));
            userModelRepository.save(user);
            return true;
        }
        return false;
    }

    public void updatePhoto(String username, String newPhoto) {
        UserModel userModel = userModelRepository.findByUsername(username);
        userModel.setPhoto_url(newPhoto);
        userModelRepository.save(userModel);
    }

    public List<UserPOJO> getOtherUsers(String username) {
        List<UserPOJO> retList = new ArrayList<>();
        for (UserModel user: userModelRepository.findAll() ) {
            if (!user.getUsername().equals(username) && !user.getUsername().equals("admin")){
                retList.add(user.convertToPOJO());
            }
        }
        return retList;
    }

    public void banUser(String username) {
        UserModel userModel = userModelRepository.findByUsername(username);
        userModel.setEnabled(false);
        userModelRepository.save(userModel);
    }
}
