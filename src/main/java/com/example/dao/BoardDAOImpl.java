package com.example.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
    private SqlSession session;

	@Override
	public List<BoardVO> boardList() {
		return session.selectList("boardList");
	}

	@Override
	public BoardVO boardRead(Long num) {
		return session.selectOne("boardOneList", num);
	}

	@Override
	public void boardInsert(BoardVO boardVO) {
		session.insert("boardInsert", boardVO);
	}

	public void boardUpdate(BoardVO boardVO) {
		session.update("boardUpdate", boardVO);
	}

	@Override
	public void boardDelete(Long num) {
		session.delete("boardDelete", num);
	}
}
