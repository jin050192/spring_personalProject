package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.vo.OrderVO;
import com.spring.project.vo.TotalVO;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	// 컨넥션풀 객체를 보관
	DataSource dataSource;
			
	@Override
	public int insertOrder(OrderVO vo) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.insertOrder(vo);		
	}

	@Override
	public ArrayList<OrderVO> getOrderList(String id) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getOrderList(id);
	}

	@Override
	public int sumOrderList(String id) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.sumOrderList(id);
	}

	@Override
	public int updateStatus(String id) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.updateStatus(id);
	}

	@Override
	public int deleteOrder(String id) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.deleteOrder(id);
	}

	@Override
	public int getOrderCnt(String id) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getOrderCnt(id);
	}

	@Override
	public ArrayList<OrderVO> getOrderList2(Map<String, Object> map) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getOrderList2(map);
	}

	@Override
	public int cancelStatus(Map<String, Object> map) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.cancelStatus(map);
	}
	
	@Override
	public int refundProduct(int orderNum) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.refundProduct(orderNum);
	}
	
	// 주문취소, 환불요청 승인하면 삭제
	@Override
	public int deletePro(int orderNum) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.deletePro(orderNum);
	}
	
	// 주문대기목록
	@Override
	public int getCnt1() {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getCnt1();
	}

	// 주문요청 리스트
	@Override
	public ArrayList<OrderVO> getOrderList3(Map<String, Object> map) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getOrderList3(map);	
	}

	@Override
	public int confirmOrder(int orderNum) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.confirmOrder(orderNum);
	}

	@Override
	public int confirmDelivery(int orderNum) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.confirmDelivery(orderNum);
	}
	
	// 배송중 상품 갯수
	@Override
	public int getCnt2() {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getCnt2();
	}
	
	@Override
	public ArrayList<OrderVO> getOrderList4(Map<String, Object> map) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getOrderList4(map);
	}

	// 주문취소 상품 갯수
	@Override
	public int getCnt3() {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getCnt3();
	}

	@Override
	public ArrayList<OrderVO> getOrderList5(Map<String, Object> map) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getOrderList5(map);
	}

	@Override
	public int getCnt4() {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getCnt4();
	}

	@Override
	public ArrayList<OrderVO> getOrderList6(Map<String, Object> map) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getOrderList6(map);
	}
	
	// 배송완료 상품 리스트
	@Override
	public int getCnt5() {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getCnt5();
	}

	@Override
	public ArrayList<OrderVO> getOrderList7(Map<String, Object> map) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getOrderList7(map);
	}

	@Override
	public int refundPro(int orderNum) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.refundPro(orderNum);
	}



	@Override
	public int rejectRefund(int orderNum) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.rejectRefund(orderNum);
	}
	// 환불승인 상품 리스트
	@Override
	public int getCnt6() {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getCnt6();
	}

	@Override
	public ArrayList<OrderVO> getOrderList8(Map<String, Object> map) {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.getOrderList8(map);
	}

	// 해결책? 결산 vo 만들어서 리턴값 받아서 jsonArray에 다시 넣기!!(내생각)
	@Override
	public ArrayList<TotalVO> total() {
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.total();
	}
}
