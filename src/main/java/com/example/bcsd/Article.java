package com.example.bcsd;

public class Article {
    private long id;
    private String title;
    private String content;

    public Article() {}

    public long getId() {return id;}

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}
