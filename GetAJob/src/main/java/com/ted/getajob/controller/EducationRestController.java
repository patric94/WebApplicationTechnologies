package com.ted.getajob.controller;

import com.ted.getajob.model.Education;
import com.ted.getajob.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class EducationRestController {

    @Autowired
    private EducationService educationService;


    @PostMapping("/api/education/add/{username}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void addEducation(@PathVariable String username, @RequestBody Education education) {
        educationService.addEducation(username, education);
    }

    @GetMapping("/api/education/{username}")
    @PreAuthorize("hasRole('USER')")
    public Set<Education> getUserEducation(@PathVariable String username){
        return educationService.getEducation(username);
    }

    @GetMapping("/api/public/education/{username}")
    @PreAuthorize("hasRole('USER')")
    public Set<Education> getPublicEducations(@PathVariable String username) {
        return educationService.getPublicEducations(username);
    }

    @PutMapping("/api/education/set/public/{id}")
    @PreAuthorize("hasRole('USER')")
    public void setEducationPublic(@PathVariable Long id) {
        educationService.setPublic(id);
    }

    @PutMapping("/api/education/set/private/{id}")
    @PreAuthorize("hasRole('USER')")
    public void setEducationPrivate(@PathVariable Long id) {
        educationService.setPrivate(id);
    }
}
