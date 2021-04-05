package com.spring.project.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.vo.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public int getProCnt1() {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.getProCnt1();
	}
	
	@Override
	public List<ProductVO> productList1(Map<String, Object> map) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.productList1(map);
	}

	@Override
	public int getProCnt2() {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.getProCnt2();
	}
	
	@Override
	public List<ProductVO> productList2(Map<String, Object> map) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.productList2(map);
	}

	@Override
	public int getProCnt3() {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.getProCnt3();
	}
	
	@Override
	public List<ProductVO> productList3(Map<String, Object> map) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.productList3(map);
	}

	@Override
	public int getProCnt4() {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.getProCnt4();
	}
	
	@Override
	public List<ProductVO> productList4(Map<String, Object> map) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.productList4(map);
	}

	@Override
	public int getProCnt5() {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.getProCnt5();
	}
	
	@Override
	public List<ProductVO> productList5(Map<String, Object> map) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.productList5(map);
	}

	@Override
	public ProductVO getInfo(int num) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.getInfo(num);
	}

	@Override
	public void addReadCnt(int num) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		dao.addReadCnt(num);
	}

	@Override
	public List<ProductVO> bestItems() {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.bestItems();
	}

	@Override
	public List<ProductVO> newItems() {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.newItems();
	}

	@Override
	public int getProCnt() {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.getProCnt();
	}
	
	@Override
	public List<ProductVO> productList(Map<String, Object> map) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.productList(map);
	}

	@Override
	public int putProduct(ProductVO vo) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.putProduct(vo);
	}

	@Override
	public int updateProduct(ProductVO vo) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.updateProduct(vo);
	}

	@Override
	public int deleteProduct(int num) {
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		return dao.deleteProduct(num);
	}
		
}
