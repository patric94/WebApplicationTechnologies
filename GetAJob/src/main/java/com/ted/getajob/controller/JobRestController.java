package com.ted.getajob.controller;

import com.ted.getajob.model.Job;
import com.ted.getajob.model.pojo.UserPOJO;
import com.ted.getajob.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class JobRestController {

    @Autowired
    private JobService jobService;

    @PostMapping("/api/post/job/{username}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void postJob(@PathVariable String username, @RequestBody Job job) {
        jobService.addJob(username, job);
    }

    @GetMapping("/api/get/jobs/{username}")
    @PreAuthorize("hasRole('USER')")
    public Set<Job> getMyJobs(@PathVariable String username) {
        return jobService.getMyJobs(username);
    }

    @GetMapping("/api/get/friend/jobs/{username}")
    @PreAuthorize("hasRole('USER')")
    public Set<Job> getFriendJobs(@PathVariable String username) {
        return jobService.getFriendJobs(username);
    }

    @PostMapping("/api/apply/job/{id}/{username}")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void applyForJob(@PathVariable Long id, @PathVariable String username) {
        jobService.applyForJob(id, username);
    }

    @GetMapping("/api/get/job/applicants/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<UserPOJO> getApplicants(@PathVariable Long id) {
        return jobService.getJobApplicants(id);
    }

    @GetMapping("/api/get/applied/to/job/{id}/{username}")
    @PreAuthorize("hasRole('USER')")
    public int getIfAppliedToJob(@PathVariable Long id, @PathVariable String username) {
        return jobService.getIfAppliedToJob(id, username);
    }
}
