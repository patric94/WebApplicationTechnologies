package com.ted.getajob.service;

import com.ted.getajob.model.Education;
import com.ted.getajob.model.UserModel;
import com.ted.getajob.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void addEducation(String username, Education education) {
        UserModel userModel = userService.getUserByUsername(username);
        education.setUserModel(userModel);
        userModel.getEducations().add(education);
        educationRepository.save(education);
    }

    public Set<Education> getEducation(String username) {
        return userService.getUserByUsername(username).getEducations();
    }

    public Set<Education> getPublicEducations(String username) {
        Set<Education> retSet = new HashSet<>();
        for (Education education : userService.getUserByUsername(username).getEducations()) {
            if (education.getPublic()) {
                retSet.add(education);
            }
        }
        return retSet;
    }

    public void setPublic(Long id) {
        Education education = educationRepository.getOne(id);
        education.setPublic(true);
        educationRepository.save(education);
    }

    public void setPrivate(Long id) {
        Education education = educationRepository.getOne(id);
        education.setPublic(false);
        educationRepository.save(education);
    }
}
