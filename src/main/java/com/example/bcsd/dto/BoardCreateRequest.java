package com.example.bcsd.dto;

import jakarta.validation.constraints.NotBlank;

public class BoardCreateRequest {

    @NotBlank(message = "게시판 이름은 필수입니다.")
    private String name;

    public BoardCreateRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
