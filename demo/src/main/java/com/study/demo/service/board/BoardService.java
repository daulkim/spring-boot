package com.study.demo.service.board;

import com.study.demo.domain.board.Board;
import com.study.demo.domain.dto.BoardCreateRequestDto;
import com.study.demo.domain.board.BoardRepository;
import com.study.demo.domain.dto.BoardReponseDto;
import com.study.demo.domain.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardCreateRequestDto board) {
        return boardRepository.save(board.toEntity()).getId();
    }

    public BoardReponseDto findBoard(Long id) {
        Board entity = boardRepository.findById(id)
                            .orElseThrow(() ->
                                    new IllegalArgumentException("No such data : " + id));

        return new BoardReponseDto(entity);

    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                                        .orElseThrow(() ->
                                                new IllegalArgumentException("No such data" + id));
        board.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
}
