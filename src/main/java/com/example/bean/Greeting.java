package com.example.bean;

public class Greeting {
	private long id;
	private String content;
	
	public Greeting(long id, String Content) {
		this.id = id;
		this.content = Content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
