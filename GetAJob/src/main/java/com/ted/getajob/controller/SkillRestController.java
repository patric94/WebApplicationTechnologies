package com.ted.getajob.controller;

import com.ted.getajob.model.Skill;
import com.ted.getajob.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SkillRestController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/api/skill/add/{username}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void addSkill(@PathVariable String username, @RequestBody Skill skill) {
        skillService.addSKill(username, skill);
    }

    @GetMapping("/api/skill/{username}")
    @PreAuthorize("hasRole('USER')")
    public Set<Skill> getUserWork(@PathVariable String username){
        return skillService.getSkill(username);
    }

    @GetMapping("/api/public/skill/{username}")
    @PreAuthorize("hasRole('USER')")
    public Set<Skill> getPublicSkills(@PathVariable String username) {
        return skillService.getPublicSkills(username);
    }

    @PutMapping("/api/skill/set/public/{id}")
    @PreAuthorize("hasRole('USER')")
    public void setSkillPublic(@PathVariable Long id) {
        skillService.setPublic(id);
    }

    @PutMapping("/api/skill/set/private/{id}")
    @PreAuthorize("hasRole('USER')")
    public void setSkillPrivate(@PathVariable Long id) {
        skillService.setPrivate(id);
    }
}
