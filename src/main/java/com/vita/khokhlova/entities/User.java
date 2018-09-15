package com.vita.khokhlova.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="users")
public class User  {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false)
    private Integer id;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String firstname;

    @Column(nullable=false)
    private String lastname;

//	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
//	private List<Post> posts;

    //	@OneToMany(mappedBy="user")
//	private List<Comment> comments;
//
    @JoinTable(name = "friends", joinColumns = {
            @JoinColumn(name = "id1", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id2", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private List<User> friends;

    public User() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstname=" + firstname
                + ", lastname=" + lastname + "]";
    }

//	public List<Post> getPosts() {
//		return posts;
//	}
//
//	public void setPosts(List<Post> posts) {
//		this.posts = posts;
//	}

    //	public List<Comment> getComments() {
//		return comments;
//	}
//
//	public void setComments(List<Comment> comments) {
//		this.comments = comments;
//	}
//
    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }


}