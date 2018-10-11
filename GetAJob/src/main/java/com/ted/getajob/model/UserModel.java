package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ted.getajob.model.pojo.CommentPOJO;
import com.ted.getajob.model.pojo.PostPOJO;
import com.ted.getajob.model.pojo.UserPOJO;
import com.ted.getajob.model.pojo.UserXML;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "USER")
public class UserModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;

    @Column(name = "EMAIL", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @Column(name = "ENABLED")
//    @NotNull
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull
    private Date lastPasswordResetDate;

    @Column(name = "PHOTO_URL")
    private String photo_url;

    @Column(name = "CURR_POS")
    @NotNull
    private String curr_pos;

    @Column(name = "CURR_INST")
    private String curr_inst;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    @JsonIgnore
    private List<Authority> authorities;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "FRIENDS",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "FRIEND_ID", referencedColumnName = "ID")})
    @JsonIgnore
    private Set<UserModel> friends = new HashSet<>();

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private Set<Education> educations = new HashSet<>();

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private Set<Work> works = new HashSet<>();

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private Set<Job> myJobs = new HashSet<>();

    @ManyToMany(mappedBy = "applicants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Job> appliedJobs = new HashSet<>();

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private Set<Interest> interests = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_CHAT",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CHAT_ID", referencedColumnName = "ID")})
    @JsonIgnore
    private Set<Chat> chats = new HashSet<>();

    public UserPOJO convertToPOJO() {
        return new UserPOJO(this.username, this.firstname, this.lastname, this.firstname+' '+this.lastname, this.photo_url, this.curr_pos, this.curr_inst);
    }

    public UserXML convertToUserXML() {
        Set<String> friendList = new HashSet<>();
        for (UserModel friend : this.friends) {
            friendList.add(friend.getUsername());
        }
        Set<PostPOJO> postPOJOS = new HashSet<>();
        for (Post post : this.posts) {
            postPOJOS.add(post.convertToPOJO());
        }
        Set<CommentPOJO> commentPOJOS = new HashSet<>();
        for (Comment comment : this.comments) {
            commentPOJOS.add(comment.convertToPOJO());
        }
        Set<String> jobs = new HashSet<>();
        for (Job job : this.myJobs) {
            jobs.add(job.getTitle());
        }
        return new UserXML(this.username, this.firstname, this.lastname, this.photo_url, this.curr_pos, this.curr_inst, friendList, jobs, postPOJOS, commentPOJOS);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getCurr_pos() {
        return curr_pos;
    }

    public void setCurr_pos(String curr_pos) {
        this.curr_pos = curr_pos;
    }

    public String getCurr_inst() {
        return curr_inst;
    }

    public void setCurr_inst(String curr_inst) {
        this.curr_inst = curr_inst;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<UserModel> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserModel> friends) {
        this.friends = friends;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Set<Work> getWorks() {
        return works;
    }

    public void setWorks(Set<Work> works) {
        this.works = works;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Job> getMyJobs() {
        return myJobs;
    }

    public void setMyJobs(Set<Job> myJobs) {
        this.myJobs = myJobs;
    }

    public Set<Job> getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(Set<Job> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Interest> getLikes() {
        return interests;
    }

    public void setLikes(Set<Interest> interests) {
        this.interests = interests;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }
}
