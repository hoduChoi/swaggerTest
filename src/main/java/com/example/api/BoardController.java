package com.example.api;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.BoardVO;
import com.example.service.BoardService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
//	@ApiOperation(value="테스트 API")
//	@ApiImplicitParams({ //parameter required, dataType 지정가능. 
//		@ApiImplicitParam(name = "num", value = "글번호", required = true, dataType = "long", paramType = "query"),
//	})
	
	/**
	 * 글 전체 조회 API 
	 * @param boardVO
	 * @return list
	 */
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	@ResponseBody
	public List<BoardVO> boardList() {
		List<BoardVO> list = service.boardList();
		logger.debug("boardList: "+list);
		
		return list;
	}
	
	/**
	 * 특정 글 조회 API
	 * @param num
	 * @return boardVO
	 */
	@RequestMapping(value="/boardRead/{num}", method=RequestMethod.GET)
	@ResponseBody
	public BoardVO boardRead(@PathVariable("num") Long num){
		BoardVO boardData = service.boardRead(num);
		logger.debug("boardOneList: "+ boardData);
		return boardData;
	}
	
	/**
	 * 글 등록 
	 * @param boardVO
	 */
	/*response 출력*/
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	@ResponseBody
	public String boardInsert(@RequestBody BoardVO boardVO) {
		logger.debug("boardVO: "+boardVO);
		//성공시
		try {
			service.boardInsert(boardVO);
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//업데이트,딜리트 
	/*{
  		"content": "dddddd222222",
  		"num": 1,
  		"title": "ddddd",
  		"writeDate": "2019-03-14T05:38:35.341Z"
	}요것만 되게, response 출력
*/
	/**
	 * 글 수정 
	 * @param boardVO
	 */
	@RequestMapping(value="/boardUpdate", method=RequestMethod.PUT)
	@ResponseBody
	public String boardUpdate(@RequestBody BoardVO boardVO) {
		logger.debug("updateParam: "+boardVO);
		try {
			service.boardUpdate(boardVO);
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	/*real delete api response 출력 필요.*/
	/**
	 * 글 삭제
	 * @param num
	 */
	
	@RequestMapping(value="/boardDelete/{num}", method=RequestMethod.DELETE)
	@ResponseBody
	public String boardDelete(@PathVariable Long num) {
		logger.debug("updateParam: "+num);
		try {
			service.boardDelete(num);
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
}
