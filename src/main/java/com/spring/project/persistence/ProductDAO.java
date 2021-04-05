package com.spring.project.persistence;

import java.util.List;
import java.util.Map;

import com.spring.project.vo.ProductVO;

public interface ProductDAO {
	// 1번 리스트 갯수 확인
	public int getProCnt1();
	
	// 1번 리스트
	public List<ProductVO> productList1(Map<String, Object> map);

	// 2번 리스트 갯수 확인
	public int getProCnt2();
	
	// 2번 리스트
	public List<ProductVO> productList2(Map<String, Object> map);

	// 3번 리스트 갯수 확인
	public int getProCnt3();	

	// 3번 리스트
	public List<ProductVO> productList3(Map<String, Object> map);	

	// 4번 리스트 갯수 확인
	public int getProCnt4();	
	
	// 4번 리스트
	public List<ProductVO> productList4(Map<String, Object> map);	

	// 5번 리스트 갯수 확인
	public int getProCnt5();
	
	// 5번 리스트
	public List<ProductVO> productList5(Map<String, Object> map);
	
	// 조회수 증가
	public void addReadCnt(int num);
	
	// 상세 정보 불러오기
	public ProductVO getInfo(int num);
	
	// 베스트 아이템 리스트
	public List<ProductVO> bestItems();
	
	// 뉴 아이템 리스트
	public List<ProductVO> newItems();	
	
	// 재고 아이템 리스트 갯수
	public int getProCnt();
		
	// 재고 아이템 리스트
	public List<ProductVO> productList(Map<String, Object> map);
	
	// 재고 상품 추가
	public int putProduct(ProductVO vo);
	
	// 재고 상품 수정
	public int updateProduct(ProductVO vo);
	
	// 재고 상품 삭제
	public int deleteProduct(int num);
}
