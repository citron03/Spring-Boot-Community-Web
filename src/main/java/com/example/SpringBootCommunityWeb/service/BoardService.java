package com.example.SpringBootCommunityWeb.service;


import com.example.SpringBootCommunityWeb.domain.Board;
import com.example.SpringBootCommunityWeb.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> findBoardList(Pageable pageable){

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());

        return boardRepository.findAll(pageable);

    }

    public Board findBoardByIdx(Long idx){

        // board의 idx값을 사용하여 board 객체를 반환한다.
        return boardRepository.findById(idx).orElse(new Board());
    }

}
