package com.cafe24.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.UserService;

// 똑같은 이름의 UserController 가 있기 때문에 충돌을 피하기 위해 Controller 에 ID를 준다.
@Controller("userAPIController")
@RequestMapping("/user/api")
public class UserConroller {
	
	@Autowired
	private UserService userService;
	
//	@ResponseBody
//	@RequestMapping("/checkemail")
//	public Map<String, Object> checkEmail(
//			@RequestParam(value="email", required=true, defaultValue="") String email){
//		Boolean exist = userService.existEmail(email);
//		
////		JSONResult result = new JSONResult();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("result", "success");
//		map.put("data", exist);
////		map.put("message", ".....");
//		return map;
//	}
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail(
			@RequestParam(value="email", required=true, defaultValue="") String email){
		Boolean exist = userService.existEmail(email);
		return JSONResult.success(exist);
	}
}
