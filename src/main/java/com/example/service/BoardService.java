package com.example.service;

import java.util.List;

import com.example.dto.BoardVO;

public interface BoardService {
	public List<BoardVO> boardList(BoardVO boardVO);
}
