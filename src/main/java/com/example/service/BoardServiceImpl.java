package com.example.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.dao.BoardDAO;
import com.example.dto.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO dao; 
	
	@Override
	public List<BoardVO> boardList(BoardVO boardVO) {
		// TODO Auto-generated method stub
		return dao.boardList(boardVO);
	}

}
