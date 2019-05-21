package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	@RequestMapping("/list")
	public String list() {
		return "board/list";
	}

	@RequestMapping("/write")
	public String write() {
		return "board/write";
	}
	
	@RequestMapping("/view")
	public String view() {
		return "board/view";
	}
	
	@RequestMapping("/modify")
	public String modify() {
		return "board/modify";
	}
	
	
}
