package com.example.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="MessageDto", description="TODO")
public class MessageDto {
	@ApiModelProperty(value = "Message content text", required = true, example = "some demo message")
    private String content;
	private String name; 
	
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getName() {
		return name;
	}

}
