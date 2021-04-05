package com.spring.project.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface CartService {
	// 카트 리스트 불러오기
	public void cartList(HttpServletRequest req, Model model);
	// 카트에 상품 추가
	public void addCart(HttpServletRequest req, Model model);
	// 상품 수량 수정
	public void updateCart(HttpServletRequest req, Model model);
	// 선택 상품 삭제
	public void deleteCart(HttpServletRequest req, Model model);
}
