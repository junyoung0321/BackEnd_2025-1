package com.example.bcsd.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ArticleCreateRequest {

    @NotNull(message = "작성자 ID(authorId)는 필수입니다.")
    private Long authorId;

    @NotNull(message = "게시판 ID(boardId)는 필수입니다.")
    private Long boardId;

    @NotBlank(message = "제목은 비어있을 수 없습니다.") // null, "", " " 모두 허용 안 함
    private String title;

    @NotBlank(message = "내용은 비어있을 수 없습니다.")
    private String content;

    // 기본 생성자
    public ArticleCreateRequest() {}

    // Getter & Setter
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public Long getBoardId() { return boardId; }
    public void setBoardId(Long boardId) { this.boardId = boardId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
