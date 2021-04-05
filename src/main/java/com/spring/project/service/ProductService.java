package com.spring.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ProductService {
	// 대형꽃다발 리스트
	public void productList1(HttpServletRequest req, Model model);
	// 머니플라워 리스트
	public void productList2(HttpServletRequest req, Model model);
	// 생화 리스트
	public void productList3(HttpServletRequest req, Model model);
	// 장미 100송이
	public void productList4(HttpServletRequest req, Model model);
	// 시들지 않는꽃
	public void productList5(HttpServletRequest req, Model model);
	// 상세정보
	public void productInfo(HttpServletRequest req, Model model);
	// 베스트 탑4 및 뉴아이템 탑4
	public void bestItems(HttpServletRequest req, Model model);
	// 관리자 상품 리스트
	public void productList(HttpServletRequest req, Model model);
	// 관리자 상품 추가
	public void inventory_add(MultipartHttpServletRequest req, Model model);
	// 관리자 상품 정보 불러오기
	public void getProductInfo(HttpServletRequest req, Model model);
	// 관리자 상품 정보 수정처리
	public void updatePro(HttpServletRequest req, Model model);
	// 관리자 상품 삭제처리
	public void deletePro(HttpServletRequest req, Model model);
}
