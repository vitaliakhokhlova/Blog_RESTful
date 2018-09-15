package com.vita.khokhlova.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(nullable=false, length=1000)
    private String text;
    @Column(nullable=false)
    private Date date = new Date();
    private int likes;

    @ManyToOne
    @JoinColumn(name="userid")
    private User user;

    @ManyToOne
    @JoinColumn(name="parentid")
    private Post parent;

//    @OneToMany(mappedBy="parent")
//    private List<Post> comments = new ArrayList<Post>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

//    public List<Post> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Post> comments) {
//        this.comments = comments;
//    }
}
