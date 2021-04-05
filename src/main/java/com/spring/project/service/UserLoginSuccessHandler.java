package com.spring.project.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.spring.project.vo.UserVO;

// 로그인이 성공한 경우 자동으로 실행
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		UserVO vo = (UserVO) authentication.getPrincipal();
		System.out.println("UserVO ==> " + vo);
		
		request.setAttribute("selectCnt", 1);
		if(authentication.getName().equals("관리자")) {
			request.getSession().setAttribute("hostId", authentication.getName());
			request.getSession().setMaxInactiveInterval(60 * 60); // 유효시간 한시간
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/loginPro.ho");
			dispatcher.forward(request, response);
			
		} else {
			request.getSession().setAttribute("memId", authentication.getName());
			request.getSession().setMaxInactiveInterval(60 * 60); // 유효시간 한시간
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/loginPro.do");
			dispatcher.forward(request, response);
		}
		
	}
}
