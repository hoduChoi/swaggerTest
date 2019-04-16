package com.example.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.BoardVO;
import com.example.dto.BoardVORequest;
import com.example.dto.MessageDto;
import com.example.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.ResponseHeader;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/board")
@Api(value="boardController"/*class name*/, description="게시판 API"/*description*/, basePath="/board"/*base path*/, tags="devices"/*name*/)
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	
	/*GET*/
		/*파라미터 없는 경우*/
		/**
		 * 글 전체 조회 API 
		 * @param boardVO
		 * @return list
		 */
		@ApiOperation(value="Board List API", notes="Board List를 반환하는 API") //해당 Api에 대한 설명 
		@ApiResponses(value= {@ApiResponse(code = 200, message="Success List", responseHeaders=@ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Boolean.class)), @ApiResponse(code = 0, message="Error")})
		@RequestMapping(value="/boardList", produces= {"application/json"}, method=RequestMethod.GET)
		@ResponseBody
		public List<BoardVO> boardList() {
			List<BoardVO> list = service.boardList();
			logger.debug("boardList: "+list);
			
			return list;
		}
	
		/*파라미터 있는 경우*/
			/*PathVariable*/
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
			
			/*RequestParam*/
			/*변수*/
			/**
			 * 특정 글 조회 API
			 * @param num
			 * @return boardVO
			 */
			@RequestMapping(value="/boardReadParam", method=RequestMethod.GET)
			@ResponseBody
			//public BoardVO boardReadParam(@ApiParam(value="번호", defaultValue="1",required=true) @RequestParam Long num, @ApiParam(value="이름", defaultValue="1", required=false) @RequestParam String name){
			@ApiImplicitParams({@ApiImplicitParam(name="num", value="글 번호", paramType="query", dataType="long", defaultValue="1"), 
								@ApiImplicitParam(name="name", value="이름", paramType="query", dataType="string")})
			public BoardVO boardReadParam(){
				BoardVO boardData = null;
				logger.debug("boardOneList: "+ boardData);
				return boardData;
			}
			
			/*객체*/
			@RequestMapping(value="/boardReadObject", method=RequestMethod.GET)
			@ResponseBody
			public BoardVO boardReadObject(@RequestBody BoardVO boardVO){
				BoardVO boardData = null;
				logger.debug("boardOneList: "+ boardData);
				return boardData;
			}
			
	/*POST*/
		/*등록*/
			/**
			 * 글 등록 
			 * @param boardVO
			 */
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
			
		/*수정*/
}
