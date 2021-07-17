package com.study.demo.domain.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {
    
    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    public void cleanup() {
        boardRepository.deleteAll();
    }

    @Test
    public void findAll() {
        //given
        String title = "title";
        String content = "content";
        
        //when
        boardRepository.save( Board.builder()
                                    .title(title)
                                    .content(content)
                                    .writer("tester")
                                    .build());
        List<Board> list = boardRepository.findAll();

        //then
        Board board = list.get(0);
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
    }
}