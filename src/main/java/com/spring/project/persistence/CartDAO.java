package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.Map;

import com.spring.project.vo.CartVO;

public interface CartDAO {
	// 카트 수량 확인
	public int getCartCnt(String id);
	
	// 카트 리스트 불러오기
	public ArrayList<CartVO> getCartList(String id);
	
	// 카트 같은 상품있는지 확인 
	public int confirmCart(CartVO vo);
	
	// 카트에 같은 상품 수량 추가(update)
	public int updateCart(CartVO vo);
	
	// 카트에 상품 추가(insert)
	public int insertCart(CartVO vo);
	
	// 카트 상품 추가 최종
	public int addCart(CartVO vo);
	
	// 장바구니 금액 합계 구하기
	public int sumCart(String id);
	
	// 수량 수정
	public int updateCount(Map<String, Object> map);
	
	// 해당 상품 삭제
	public int deleteProduct(int cartNum);
	
}
