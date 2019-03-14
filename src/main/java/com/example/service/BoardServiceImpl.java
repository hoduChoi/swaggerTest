package com.example.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.BoardController;
import com.example.dao.BoardDAO;
import com.example.dto.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardDAO dao; 
	
	@Override
	public List<BoardVO> boardList() {
		return dao.boardList();
	}

	@Override
	public BoardVO boardRead(Long num) {
		return dao.boardRead(num);
	}

	@Override
	public void boardInsert(BoardVO boardVO) {
		dao.boardInsert(boardVO);
	}

	@Override
	public void boardUpdate(BoardVO boardVO) {
		dao.boardUpdate(boardVO);
		
	}

	@Override
	public void boardDelete(Long num) {
		dao.boardDelete(num);
	}

}
