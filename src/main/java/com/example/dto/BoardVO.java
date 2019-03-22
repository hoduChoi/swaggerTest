package com.example.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Board", description="Board Model") //ui에서 체크를 할 수가 없음.
public class BoardVO {
//	
	//@ApiModelProperty(hidden = true)
	private Long num;
	@ApiModelProperty(value="이름", allowableValues="a,b")
    private String name;
	@ApiModelProperty(example="제목")
    private String title;
	@ApiModelProperty(example="내용")
    private String content;
//    @JsonIgnore json 변환을 막아버리니까 안됨.
    @ApiModelProperty(hidden = true)
    private Long readCount;
    @ApiModelProperty(example="작성일")
    private Date writeDate;
	
    public Long getNum() {
		return num;
	}
    
	public void setNum(Long num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getReadCount() {
		return readCount;
	}
	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
 
}
