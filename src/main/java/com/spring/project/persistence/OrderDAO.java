package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.Map;

import com.spring.project.vo.OrderVO;
import com.spring.project.vo.TotalVO;

public interface OrderDAO {
	// order리스트에 추가
	public int insertOrder(OrderVO vo);
	// order 리스트 뿌리기
	public ArrayList<OrderVO> getOrderList(String id);
	// order 리스트 금액 합계
	public int sumOrderList(String id);
	// 주문 진행 주문상태(0 -> 1)
	public int updateStatus(String id);
	// 주문 취소
	public int deleteOrder(String id);
	// 고객 주문상품 갯수
	public int getOrderCnt(String id);
	// 고객 주문상태 1인 order리스트 뿌리기
	public ArrayList<OrderVO> getOrderList2(Map<String, Object> map);
	// 주문취소 요청
	public int cancelStatus(Map<String, Object> map);
	// 주문취소, 환불요청시 해당 주문 삭제
	public int deletePro(int orderNum);
	// 고객 전체 주문요청 상품 갯수
	public int getCnt1();
	// 고객 전체 주문요청리스트 뿌리기 
	public ArrayList<OrderVO> getOrderList3(Map<String, Object> map);
	// 고객 전체 배송중 상품 갯수
	public int getCnt2();
	// 고객 전체 배송중 리스트
	public ArrayList<OrderVO> getOrderList4(Map<String, Object> map);
	// 고객 전체 주문취소 상품 갯수
	public int getCnt3();		
	// 고객 전체 주문 취소 리스트
	public ArrayList<OrderVO> getOrderList5(Map<String, Object> map);
	// 고객 전체 환불요청 상품 갯수
	public int getCnt4();	
	// 고객 전체 환불 요청 리스트
	public ArrayList<OrderVO> getOrderList6(Map<String, Object> map);
	// 고객 전체 배송 완료 상품 갯수
	public int getCnt5();	
	// 고객 전체 배송 완료 리스트
	public ArrayList<OrderVO> getOrderList7(Map<String, Object> map);
	// 고객 전체 배송 완료 상품 갯수
	public int getCnt6();	
	// 고객 전체 환불 승인 리스트
	public ArrayList<OrderVO> getOrderList8(Map<String, Object> map);
	// 주문 승인처리
	public int confirmOrder(int orderNum);
	// 배송완료 처리하기
	public int confirmDelivery(int orderNum);
	// 환불요청
	public int refundProduct(int orderNum);
	// 환불 승인
	public int refundPro(int orderNum);
	// 환불 거부
	public int rejectRefund(int orderNum);
	// 결산 총매출액
	public ArrayList<TotalVO> total();
}
