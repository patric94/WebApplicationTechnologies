package com.ted.getajob.service;

import com.ted.getajob.model.UserModel;
import com.ted.getajob.model.Work;
import com.ted.getajob.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void addWork(String username, Work work) {
        UserModel userModel = userService.getUserByUsername(username);
        work.setUserModel(userModel);
        userModel.getWorks().add(work);
        workRepository.save(work);
    }

    public Set<Work> getWork(String username) {
        return userService.getUserByUsername(username).getWorks();
    }

    public Set<Work> getPublicWorks(String username) {
        Set<Work> retSet = new HashSet<>();
        for (Work work : userService.getUserByUsername(username).getWorks()){
            if(work.getPublic()){
                retSet.add(work);
            }
        }
        return retSet;
    }

    public void setPublic(Long id) {
        Work work = workRepository.getOne(id);
        work.setPublic(true);
        workRepository.save(work);
    }

    public void setPrivate(Long id) {
        Work work = workRepository.getOne(id);
        work.setPublic(false);
        workRepository.save(work);
    }
}
