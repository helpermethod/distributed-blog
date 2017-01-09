package de.predic8.boot.dao;

import java.util.List;

public class FullPost {
    private String title;
    private String content;
    private List<Comment> comments;

    public FullPost(String title, String content, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
