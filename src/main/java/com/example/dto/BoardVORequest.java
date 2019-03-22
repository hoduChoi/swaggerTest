package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class BoardVORequest extends BoardVO{
	
	@JsonIgnore
	@Override
	public void setContent(String content) {
		super.setContent(content);
	}
 
}
