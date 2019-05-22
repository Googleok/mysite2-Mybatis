package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping({ "/list", "" })
	public String list(Model model) {
		model.addAttribute("list", boardService.getList());
		System.out.println(boardService.getList());
		return "/board/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "/board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo,
						HttpSession session) {
		boolean result = boardService.write(boardVo, session.getAttribute("authUser"));
		if (result) {
			System.out.println("insert: success");
		}
		return "redirect:/board/list";
	}

	@RequestMapping("/view")
	public String view() {
		return "/board/view";
	}

	@RequestMapping("/modify")
	public String modify() {
		return "/board/modify";
	}

}
