package com.spring.project.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BoardService {
	
	// 게시판 리스트
	public void boardList(HttpServletRequest req, Model model);
	
	// 게시판 상세정보
	public void contentForm(HttpServletRequest req, Model model);
	
	// 글수정 페이지
	public void modifyView(HttpServletRequest req, Model model);
	
	// 글수정 처리
	public void modifyPro(HttpServletRequest req, Model model);
	
	// 글쓰기 페이지
	public void writeForm(HttpServletRequest req, Model model);
	
	// 글쓰기 처리
	public void writePro(HttpServletRequest req, Model model);
	
	// 글삭제 처리
	public void deletePro(HttpServletRequest req, Model model);
	
	// 내가 쓴글만 나오는 게시판 리스트
	public void myboardList(HttpServletRequest req, Model model);
}
