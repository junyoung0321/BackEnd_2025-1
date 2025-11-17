package com.example.bcsd.Model;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Long boardId;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
    private String authorName;
    private String boardName;

    public Article(String title, String content, Long authorId, Long boardId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.boardId = boardId;
        this.createTime = LocalDateTime.now();
        this.modifiedTime = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public Long getBoardId() { return boardId; }
    public void setBoardId(Long boardId) { this.boardId = boardId; }

    public LocalDateTime getcreateTime() { return createTime; }
    public void setcreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    public LocalDateTime getmodifiedTime() { return modifiedTime; }
    public void setmodifiedTime(LocalDateTime modifiedTime) { this.modifiedTime = modifiedTime; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getBoardName() { return boardName; }
    public void setBoardName(String boardName) { this.boardName = boardName; }
}
