package com.ted.getajob.service;

import com.ted.getajob.model.Skill;
import com.ted.getajob.model.UserModel;
import com.ted.getajob.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void addSKill(String username, Skill skill) {
        UserModel userModel = userService.getUserByUsername(username);
        skill.setUserModel(userModel);
        userModel.getSkills().add(skill);
        skillRepository.save(skill);
    }

    public Set<Skill> getSkill(String username) {
        return userService.getUserByUsername(username).getSkills();
    }

    public Set<Skill> getPublicSkills(String username) {
        Set<Skill> retSet = new HashSet<>();
        for (Skill skill : userService.getUserByUsername(username).getSkills()){
            if(skill.getPublic()){
                retSet.add(skill);
            }
        }
        return retSet;
    }

    public void setPublic(Long id) {
        Skill skill = skillRepository.getOne(id);
        skill.setPublic(true);
        skillRepository.save(skill);
    }

    public void setPrivate(Long id) {
        Skill skill = skillRepository.getOne(id);
        skill.setPublic(false);
        skillRepository.save(skill);
    }
}
