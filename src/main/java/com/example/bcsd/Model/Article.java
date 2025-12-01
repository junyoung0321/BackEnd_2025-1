package com.example.bcsd.Model;

import java.sql.Timestamp;

public class Article {
    private Long id;
    private Long authorId;
    private Long boardId;
    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    // 기본 생성자
    public Article() {}

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public Long getBoardId() { return boardId; }
    public void setBoardId(Long boardId) { this.boardId = boardId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Timestamp getCreatedDate() { return createdDate; }
    public void setCreatedDate(Timestamp createdDate) { this.createdDate = createdDate; }

    public Timestamp getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(Timestamp modifiedDate) { this.modifiedDate = modifiedDate; }
}
