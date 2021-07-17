package com.study.demo.web;
import com.study.demo.domain.board.Board;
import com.study.demo.domain.board.BoardRepository;
import com.study.demo.web.dto.BoardCreateRequestDto;
import com.study.demo.web.dto.BoardUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private WebApplicationContext context;

    @AfterEach
    public void tearDown() {
        boardRepository.deleteAll();
    }

    @Test
    public void create() {
        //given
        String title = "title";
        String content = "content";
        BoardCreateRequestDto requestDto = BoardCreateRequestDto.builder()
                                                                .title(title)
                                                                .content(content)
                                                                .writer("tester")
                                                                .build();

        String url = "http://localhost:" + port + "/api/v1/board";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void update() {
        // given
        Board savedBoard = boardRepository.save(Board.builder()
                                                    .title("title")
                                                    .content("content")
                                                    .writer("tester")
                                                    .build());
        Long id = savedBoard.getId();
        String expectedTitle = "modifiedTitle";
        String expectedContent = "modifiedContent";

        BoardUpdateRequestDto requestDto =
                                BoardUpdateRequestDto.builder()
                                        .title(expectedTitle)
                                        .content(expectedContent)
                                        .build();

        String url = "http://localhost:" + port + "/api/v1/board/"+id;
        HttpEntity<BoardUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate
                                                .exchange(url,
                                                            HttpMethod.PUT,
                                                            requestEntity,
                                                            Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }
}