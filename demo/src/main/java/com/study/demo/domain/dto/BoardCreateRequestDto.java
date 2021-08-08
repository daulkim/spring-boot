package com.study.demo.domain.dto;

import com.study.demo.domain.board.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCreateRequestDto {

    private String title;
    private String content;
    private String writer;

    @Builder
    public BoardCreateRequestDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board toEntity() {
        return Board.builder()
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .build();
    }
}
