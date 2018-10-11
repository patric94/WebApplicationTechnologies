package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EDUCATION")
public class Education {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String startYear;

    @Column
    @NotNull
    private String endYear;

    @Column
    @NotNull
    private Boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel userModel;

    public Education() {
    }

    public Education(@NotNull String name, @NotNull String startYear, @NotNull String endYear, @NotNull Boolean isPublic, UserModel userModel) {
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
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

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
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
