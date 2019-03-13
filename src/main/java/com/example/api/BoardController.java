package com.example.api;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dto.BoardVO;
import com.example.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(BoardVO boardVO, Model model) {
		List<BoardVO> list = service.boardList(boardVO);
		model.addAttribute("list", list);
		
		logger.info("boardTest: "+list);
		return "home";
	}
}
