package com.ted.getajob.controller;

import com.ted.getajob.model.Work;
import com.ted.getajob.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class WorkRestController {

    @Autowired
    private WorkService workService;

    @PostMapping("/api/work/add/{username}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void addWork(@PathVariable String username, @RequestBody Work work) {
        workService.addWork(username, work);
    }

    @GetMapping("/api/work/{username}")
    @PreAuthorize("hasRole('USER')")
    public Set<Work> getUserWork(@PathVariable String username){
        return workService.getWork(username);
    }

    @GetMapping("/api/public/work/{username}")
    @PreAuthorize("hasRole('USER')")
    public Set<Work> getPublicWorks(@PathVariable String username) {
        return workService.getPublicWorks(username);
    }

    @PutMapping("/api/work/set/public/{id}")
    @PreAuthorize("hasRole('USER')")
    public void setWorkPublic(@PathVariable Long id) {
        workService.setPublic(id);
    }

    @PutMapping("/api/work/set/private/{id}")
    @PreAuthorize("hasRole('USER')")
    public void setWorkPrivate(@PathVariable Long id) {
        workService.setPrivate(id);
    }
}
