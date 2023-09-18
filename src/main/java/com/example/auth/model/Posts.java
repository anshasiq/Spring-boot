package com.example.auth.model;

import javax.persistence.*;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String Content;
    @ManyToOne
//    @JoinColumn(name = "user_id")
    private SiteUser user;

    public Posts(String content, SiteUser user) {
        Content = content;
        this.user = user;
    }

    public Long getPostId() {
        return postId;
    }

    public Posts() {
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public SiteUser getUser() {
        return user;
    }

    public void setUser(SiteUser user) {
        this.user = user;
    }
}
