package com.ted.getajob.service;

import com.ted.getajob.model.Job;
import com.ted.getajob.model.UserModel;
import com.ted.getajob.model.pojo.UserPOJO;
import com.ted.getajob.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void addJob(String username, Job job) {
        UserModel userModel = userService.getUserByUsername(username);
        job.setUserModel(userModel);
        job.setUsername(userModel.getUsername());
        job.setPhoto_url(userModel.getPhoto_url());
        userModel.getMyJobs().add(job);
        jobRepository.save(job);
    }

    public Set<Job> getMyJobs(String username) {
        return userService.getUserByUsername(username).getMyJobs();
    }

    public Set<Job> getFriendJobs(String username) {
        Set<Job> retSet = new HashSet<>();
        for (UserModel friend : userService.getFriendsOfUser(username)) {
            retSet.addAll(friend.getMyJobs());
        }
        return retSet;
    }

    @Transactional
    public void applyForJob(Long id, String username) {
        Job job = jobRepository.getOne(id);
        UserModel userModel = userService.getUserByUsername(username);
        job.getApplicants().add(userModel);
        userModel.getAppliedJobs().add(job);
    }

    public List<UserPOJO> getJobApplicants(Long id) {
        List<UserPOJO> retList = new ArrayList<>();
        for (UserModel user : jobRepository.getOne(id).getApplicants()){
            retList.add(user.convertToPOJO());
        }
        return retList;
    }

    public int getIfAppliedToJob(Long id, String username) {
        UserModel userModel = userService.getUserByUsername(username);
        Job job = jobRepository.getOne(id);
        if (userModel.getAppliedJobs().contains(job)){
            return 1;
        }
        else {
            return 0;
        }
    }
}
