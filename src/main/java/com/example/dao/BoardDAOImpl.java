package com.example.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Inject
    private SqlSession session;

	@Override
	public List<BoardVO> boardList(BoardVO boardVO) {
		// TODO Auto-generated method stub
		return session.selectList("boardList", boardVO);
	}

}
