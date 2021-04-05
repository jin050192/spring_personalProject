package com.spring.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.project.persistence.CartDAOImpl;
import com.spring.project.vo.CartVO;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDAOImpl dao;
	
	@Override
	public void cartList(HttpServletRequest req, Model model) {
		// 받은값 불러오기
		String id = (String) req.getSession().getAttribute("memId");
		int cnt = 0;         // 글갯수		
		// 카트에 담긴 갯수
		cnt = dao.getCartCnt(id);		
		// 금액 합계
		int sumMoney = 0;
		if(cnt > 0) {
			// 카트리스트 목록
			ArrayList<CartVO> list = dao.getCartList(id);
			model.addAttribute("list", list);
			sumMoney = dao.sumCart(id);
			model.addAttribute("sum", sumMoney);
		}		
		model.addAttribute("cnt", cnt);         // 글갯수		
	}

	@Override
	public void addCart(HttpServletRequest req, Model model) {		
		String id = (String) req.getSession().getAttribute("memId");
		int count = Integer.parseInt(req.getParameter("count"));
		int num = Integer.parseInt(req.getParameter("num"));
		
		int insertCnt = 0;		

		CartVO vo = new CartVO();
		vo.setId(id);
		vo.setNum(num);
		vo.setCount(count);
						
		insertCnt = dao.addCart(vo);
		
		model.addAttribute("insertCnt", insertCnt);
	}

	@Override
	public void updateCart(HttpServletRequest req, Model model) {		
		String id = (String) req.getSession().getAttribute("memId");
		
		String[] cartNum = req.getParameterValues("cartNum");
		String[] count = req.getParameterValues("count");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cartNum", cartNum);
		map.put("count", count);
		
		// 장바구니에 담긴 갯수
		int cnt = dao.getCartCnt(id);
		
		int updateCnt = 0;
		for(int i=0; i<cnt; i++) {
			map.put("cartNum", Integer.parseInt(cartNum[i]));
			map.put("count", Integer.parseInt(count[i]));
			updateCnt = dao.updateCount(map);
		}						
		model.addAttribute("updateCnt", updateCnt);
	}

	@Override
	public void deleteCart(HttpServletRequest req, Model model) {
		String[] chk = req.getParameterValues("cartN");
		
		int deleteCnt = 0;		
		
		for(int i=0; i<chk.length; i++) {			
			deleteCnt = dao.deleteProduct(Integer.parseInt(chk[i]));
		}		
		model.addAttribute("deleteCnt", deleteCnt);
	}	
}
