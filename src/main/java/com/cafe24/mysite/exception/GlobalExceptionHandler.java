package com.cafe24.mysite.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler( Exception.class )
	public void handleUserDaoException(
				HttpServletRequest request,
				HttpServletResponse response,
				Exception e
			) throws ServletException, IOException {
		// 1. 로깅 --------> 파일 저장!! 그래야 관리가 가능
		e.printStackTrace();
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		//LOGGER.error(errors.toString());
		System.out.println(errors.toString());
		
		// 2. 안내 페이지 가기 + 정상종료
		request.setAttribute("uri", request.getRequestURI());
		request.setAttribute("exception", errors.toString());
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);;
		
	}
}
