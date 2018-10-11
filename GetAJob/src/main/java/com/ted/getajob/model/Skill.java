package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SKILL")
public class Skill {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private Boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel userModel;

    public Skill() {
    }

    public Skill(@NotNull String name, @NotNull Boolean isPublic, UserModel userModel) {
        this.name = name;
        this.isPublic = isPublic;
        this.userModel = userModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
