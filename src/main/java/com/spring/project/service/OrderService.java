package com.spring.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface OrderService {
	// 주문폼 작성 불러오기
	public void getOrderForm(HttpServletRequest req, Model model);
	// 장바구니에서 여러개 상품 부를때
	public void cartToOrder(HttpServletRequest req, Model model);
	// 주문 진행 (주문상태 0 -> 1)
	public void orderPro(HttpServletRequest req, Model model);
	// 주문 취소(주문상태0 인 상품들)
	public void cancelOrder(HttpServletRequest req, Model model);
	// 해당 아이디 주문 리스트
	public void orderList(HttpServletRequest req, Model model);
	// 주문 대기중인 상품 취소 요청(주문상태 1 -> 4)
	public void updateOrder(HttpServletRequest req, Model model);
	// 환불 요청
	public void refund(HttpServletRequest req, Model model);
	// 승인대기 리스트
	public void orderList1(HttpServletRequest req, Model model);
	// 주문승인 리스트
	public void orderList2(HttpServletRequest req, Model model);
	// 주문취소요청 리스트 (상태 4 -> 삭제)
	public void orderList3(HttpServletRequest req, Model model);
	// 주문완료 리스트 (상태3)
	public void orderList4(HttpServletRequest req, Model model);
	// 환불요청 리스트 (상태5)
	public void orderList5(HttpServletRequest req, Model model);
	// 환불승인 리스트 (상태7)
	public void orderList6(HttpServletRequest req, Model model);
	// 주문승인
	public void confirmOrder(HttpServletRequest req, Model model);
	// 주문취소 승인
	public void deleteOrder(HttpServletRequest req, Model model);
	// 배송 처리
	public void confirmDelivery(HttpServletRequest req, Model model);
	// 환불 승인
	public void confirmRefund(HttpServletRequest req, Model model);
	// 환불 거부
	public void rejectRefund(HttpServletRequest req, Model model);
	// 결산
	public void closingTotal(HttpServletRequest req, Model model);
}
