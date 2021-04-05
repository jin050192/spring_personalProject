package com.spring.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MemberService {
	
	// id중복확인 처리
	public void confirmId(HttpServletRequest req, Model model);
	
	// 회원가입 처리
	public void signInPro(HttpServletRequest req, Model model);
	
	// 회원탈퇴 처리
	public void deletePro(HttpServletRequest req, Model model);
	
	// 회원정보 불러오기
	public void modifyView(HttpServletRequest req, Model model);
	
	// 회원정보 수정
	public void modifyPro(HttpServletRequest req, Model model);
	
	// 아이디 찾기
	public void findId(HttpServletRequest req, Model model);
	
	// 이메일 인증, 임시 비밀번호 변경
	public void emailChk(HttpServletRequest req, Model model);
	
	// 멤버리스트
	public void memberList(HttpServletRequest req, Model model);
	
	// 회원 강제 탈퇴
	public void deleteMember(HttpServletRequest req, Model model);
}
