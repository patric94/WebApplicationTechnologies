package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "JOB")
public class Job {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String title;

    @Column
    @NotNull
    private String description;

    @Column
    private String username;

    @Column
    private String photo_url;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel userModel;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOB_APPLICATION",
            joinColumns = {@JoinColumn(name = "JOB_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
    @JsonIgnore
    private Set<UserModel> applicants = new HashSet<>();

    public Job() {
    }

    public Job(@NotNull String title, @NotNull String description, String username, String photo_url, UserModel userModel, Set<UserModel> applicants) {
        this.title = title;
        this.description = description;
        this.username = username;
        this.photo_url = photo_url;
        this.userModel = userModel;
        this.applicants = applicants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Set<UserModel> getApplicants() {
        return applicants;
    }

    public void setApplicants(Set<UserModel> applicants) {
        this.applicants = applicants;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
