package com.study.demo.controller;

import com.study.demo.service.board.BoardService;
import com.study.demo.domain.dto.BoardCreateRequestDto;
import com.study.demo.domain.dto.BoardReponseDto;
import com.study.demo.domain.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/v1/board")
    public Long create(@RequestBody BoardCreateRequestDto requestDto) {
        return boardService.save(requestDto);
    }

    @GetMapping("/api/v1/board/{id}")
    public BoardReponseDto board(@PathVariable Long id){
        return boardService.findBoard(id);
    }

    @PutMapping("/api/v1/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }
}
