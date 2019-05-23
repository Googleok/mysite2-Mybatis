package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping({ "/list", "" })
	public String list(
			Model model,
			@RequestParam(value="page", required = true, defaultValue ="1" )Integer page	
			) {
		model.addAttribute("listCount", boardService.getListCount());
		model.addAttribute("currentPage", page);
		model.addAttribute("list", boardService.getList(page));
		return "/board/list";
	}
	
	@Auth(role=Auth.Role.USER)	// 인증 annotation
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(
			@RequestParam(value="no", required=true, defaultValue="")Long no,
			HttpSession session,
			Model model
	) {
		
//		if(session.getAttribute("authUser") == null) {
//			return "redirect:/user/login";
//		}
		
		if(no != null) {
			model.addAttribute("parentsNo", no);
		}
		
		return "/board/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		
		boolean result = boardService.write(boardVo, session.getAttribute("authUser"));
		if (result) {
			System.out.println("insert: success");
		}
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(@PathVariable(value="no")Long no, Model model) {
		BoardVo vo = boardService.getOne(no);
		model.addAttribute("oneVo", vo);
		return "/board/view";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable(value="no")Long no, Model model, HttpSession session) {
		if(session.getAttribute("authUser") == null) {
			return "redirect:/user/login";
		}
		Boolean result = boardService.delete(no);
		model.addAttribute("deleteResult", result);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@PathVariable(value="no")Long no, Model model, HttpSession session) {
		if(session.getAttribute("authUser") == null) {
			return "redirect:/user/login";
		}
		BoardVo vo = boardService.getOne(no);
		model.addAttribute("oneVo", vo);
		return "/board/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo boardVo) {
		boolean result = boardService.modify(boardVo);
		if (result) {
			System.out.println("insert: success");
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(
			@RequestParam(value = "kwd", required = true, defaultValue = "")String kwd,
			@RequestParam(value="page", required = true, defaultValue ="1" )Integer page,
			Model model) {
		List<BoardVo> list = boardService.search(kwd, page);
		model.addAttribute("listCount", list.size());
		model.addAttribute("currentPage", page);
		model.addAttribute("list", list);
		return "/board/list";
	}
	



}
