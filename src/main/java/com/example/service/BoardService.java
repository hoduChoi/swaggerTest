package com.example.service;

import java.util.List;

import com.example.dto.BoardVO;

public interface BoardService {
	public List<BoardVO> boardList();

	public BoardVO boardRead(Long num);

	public void boardInsert(BoardVO boardVO);

	public void boardUpdate(BoardVO boardVO);

	public void boardDelete(Long num);
}
