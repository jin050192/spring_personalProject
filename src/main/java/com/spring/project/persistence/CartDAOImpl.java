package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.vo.CartVO;

@Repository
public class CartDAOImpl implements CartDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int getCartCnt(String id) {
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.getCartCnt(id);
	}

	@Override
	public ArrayList<CartVO> getCartList(String id) {
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.getCartList(id);
	}

	@Override
	public int confirmCart(CartVO vo) {
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.confirmCart(vo);
	}
	
	@Override
	public int updateCart(CartVO vo) {
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.updateCart(vo);
	}
	
	@Override
	public int insertCart(CartVO vo) {
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.insertCart(vo);
	}

	@Override
	public int sumCart(String id) {
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.sumCart(id);
	}

	@Override
	public int updateCount(Map<String, Object> map) {
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.updateCount(map);
	}

	@Override
	public int deleteProduct(int cartNum) {
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.deleteProduct(cartNum);
	}

	@Override
	public int addCart(CartVO vo) {
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		int confirmCart = dao.confirmCart(vo);
		int insertCnt = 0;
		if(confirmCart == 1) {
			insertCnt = dao.updateCart(vo);
		} else {
			insertCnt = dao.insertCart(vo);
		}
		return insertCnt;
	}
}
