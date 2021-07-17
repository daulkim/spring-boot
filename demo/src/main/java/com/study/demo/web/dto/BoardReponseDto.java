package com.study.demo.web.dto;

import com.study.demo.domain.board.Board;
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
