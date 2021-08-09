package com.study.demo.dto;

import com.study.demo.entity.Board;
import lombok.Getter;

@Getter
public class BoardReponseDto {

    private Long id;
    private String title;
    private String content;
    private String writer;

    public BoardReponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
    }
}
