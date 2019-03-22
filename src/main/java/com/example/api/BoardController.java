package com.example.api;

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
@Api(value="/board", description="게시판 API", basePath="/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	/**
	 * 글 전체 조회 API 
	 * @param boardVO
	 * @return list
	 */
	@ApiOperation(value="Board List API", notes="Board List를 반환하는 API") //해당 Api에 대한 설명 
	@ApiResponses(value= {@ApiResponse(code = 200, message="Success List", responseHeaders=@ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Boolean.class)), @ApiResponse(code = 0, message="Error")})
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
	
	//implicit: 절대적인. Q. @ApiParam: 메소드 내 파라미터, @ApiImplicitParam 차이
	@ApiImplicitParams({@ApiImplicitParam(name="graph", value="list of the graph record IDs for each network which are going to be merged in a single network", paramType="body", dataType="jsonNode"), 
						@ApiImplicitParam(name="name", value="graph name", paramType="body", examples=@Example(value= {@ExampleProperty(mediaType="application/json", value="")}))})
	@RequestMapping(value="/boardRead", method=RequestMethod.GET)
	@ResponseBody
	public BoardVO boardReadParam(@ApiParam(value="설명", defaultValue="1", required=false) @RequestParam Long num){
	//	BoardVO boardData = service.boardRead(num);
	//	logger.debug("boardOneList: "+ boardData);
	//	return boardData;
		return null;
	}
	
	
	@RequestMapping(value="boardTest", method=RequestMethod.GET)
	public void test(@RequestBody @ApiParam(examples = @Example(value = {@ExampleProperty(mediaType = "application/json", value = "{'test':'test'}")}),
            name="name",value="value") Map<String, String> params) {
	}
	
	
	/**
	 * 글 등록 
	 * @param boardVO
	 */
	/*response 출력*/
	/*@ApiParam: api parameter를 제어 
	 * ex) @ApiParam(value="Description", defaultValue="Parameter DefaultValue", required="true/false") @RequestParam String test*/
	
	//@ApiImplicitParams({@ApiImplicitParam(name="name", value="User's name", required=false, dataType="string", paramType="query", defaultValue="Niklas")})
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	@ResponseBody
	public String boardInsert(@RequestBody List<BoardVORequest> persons) {
//		logger.debug("boardVO: "+boardVO);
//		//성공시
//		try {
//			service.boardInsert(boardVO);
//			return "SUCCESS";
//		}catch(Exception e) {
//			e.printStackTrace();
//			return "FAIL";
//		}
		return null;
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
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
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
	@ApiIgnore
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
	
//	@ApiModelProperty(
//		    value = "A JSON value representing a transaction. An example of the expected schema can be found down here. The fields marked with an * means that they are required.",
//		    example = { @ExampleProperty(value="{foo: whatever, bar: whatever2}")})
//	@RequestMapping(value = "/start", method = RequestMethod.POST)
//	public void startProcess(
//	    @RequestBody(required = false) BoardVO fields) {
//	    // .. does stuff
//	}
	//requestparam vs requestpart 
	//파라미터가 string이 아닌 경우에는 @RequestParam은 등록된 converter or propertyeditor를 통해 변환. @RequestPart는 requestPart의 content-type을 고려하여 HttpMessageConverters를 사용하기 떄문에 복잡한 콘텐츠 ( JSON, XML이 포함된 파라미터에 쓰이는 경우가 많음)
	//requestparam은 required default가 true
	@RequestMapping(value="/boardUpload", method=RequestMethod.POST)
	public void fileUpload(@RequestParam Long num,
			@ApiParam(value = "photo") @RequestPart MultipartFile photoFile,
	        @ApiParam(value = "excel") @RequestPart MultipartFile excelFile,
	        @ApiParam(value = "jpg") @RequestPart MultipartFile jpgFile) {
		
	}
	
	@ApiOperation(value = "simple message resource")
	@RequestMapping(value="/boardExampleValueTest", method=RequestMethod.POST, consumes= {"application/json"})
	@ResponseBody
	public void boardInsert22( @ApiParam(value = "val1")
    @RequestParam(value = "val1", required = false) List<String> val1,
    @ApiParam(value = "val2", example = "60")
    @RequestParam(value = "val2", defaultValue = "60") Integer val2,
    @ApiParam(value = "val3")
    @RequestParam(value = "val3", defaultValue = "30") Integer val3,
    @ApiParam(value = "val4")
    @RequestParam(value = "val4", defaultValue = "false", required = false) boolean val4) {

	}
	

}
