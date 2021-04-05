package com.spring.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.project.persistence.CartDAOImpl;
import com.spring.project.persistence.MemberDAOImpl;
import com.spring.project.persistence.OrderDAOImpl;
import com.spring.project.vo.MemberVO;
import com.spring.project.vo.OrderVO;
import com.spring.project.vo.TotalVO;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDAOImpl order;
	
	@Autowired
	MemberDAOImpl member;
	
	@Autowired
	CartDAOImpl cart;
	
	// 상품 한 개 바로 구매할때 orderForm
	@Override
	public void getOrderForm(HttpServletRequest req, Model model) {
		// 받은값 불러오기
		String id = (String) req.getSession().getAttribute("memId");
		int count = Integer.parseInt(req.getParameter("count"));				
		int num = Integer.parseInt(req.getParameter("num"));		
		
		OrderVO vo = new OrderVO();
		vo.setId(id);
		vo.setCount(count);
		vo.setNum(num);
		
		// 금액 합계
		int sumMoney = 0;
		int insertCnt = order.insertOrder(vo);
		if(insertCnt != 0) {
			// 목록 불러오기
			ArrayList<OrderVO> list = new ArrayList<OrderVO>();
			list = order.getOrderList(id);
			model.addAttribute("list", list);
			// 합계계산
			sumMoney = order.sumOrderList(id);
			model.addAttribute("sum", sumMoney);
		}
		// 회원 정보 불러오기
		MemberVO memInfo = new MemberVO();
		memInfo = member.getMemberInfo(id);
		
		model.addAttribute("insertCnt", insertCnt);		
		model.addAttribute("memInfo", memInfo);
	}
	
	// 카트 -> 주문
	@Override
	public void cartToOrder(HttpServletRequest req, Model model) {
		// 받은값 불러오기
		String id = (String) req.getSession().getAttribute("memId");			
		String[] num = req.getParameterValues("num");
		String[] count = req.getParameterValues("count");
		String[] cartNum = req.getParameterValues("cartNum");
		
		// 장바구니에 담긴 갯수
		int cnt = cart.getCartCnt(id);		

		// order_tbl에 넣기
		OrderVO vo = new OrderVO();
		
		int insertCnt = 0; 
		for(int i=0; i<cnt; i++) {
			vo.setId(id);
			vo.setNum(Integer.parseInt(num[i]));
			vo.setCount(Integer.parseInt(count[i]));
			insertCnt = order.insertOrder(vo);
		}							
		
		// cart_tbl 삭제
		int deleteCnt = 0;
		for(int i=0; i<cnt; i++) {
			deleteCnt = cart.deleteProduct(Integer.parseInt(cartNum[i]));
		}
		
		// 목록 불러오기
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		list = order.getOrderList(id);
		model.addAttribute("list", list);
		// 합계계산
		int sumMoney = order.sumOrderList(id);
		model.addAttribute("sum", sumMoney);
		
		// 회원 정보 불러오기	
		MemberVO memInfo = new MemberVO();
		memInfo = member.getMemberInfo(id);				
		model.addAttribute("memInfo", memInfo);		
	}

	@Override
	public void orderPro(HttpServletRequest req, Model model) {
		// 받은값 불러오기
		String id = (String) req.getSession().getAttribute("memId");		
		
		int sumMoney = order.sumOrderList(id);
		model.addAttribute("sum", sumMoney);
		
		int updateCnt = order.updateStatus(id);
		
		model.addAttribute("updateCnt", updateCnt);
	}

	@Override
	public void cancelOrder(HttpServletRequest req, Model model) {
		// 받은값 불러오기
		String id = (String) req.getSession().getAttribute("memId");			
		int deleteCnt = order.deleteOrder(id);		
		model.addAttribute("deleteCnt", deleteCnt);
	}

	@Override
	public void orderList(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 10;    // 한페이지당 출력한 글 갯수
		int pageBlock = 5;   // 한 블럭당 페이지 갯수
				
		int cnt = 0;         // 글갯수
		int start = 0;       // 현재 페이지 시작 글번호
		int end = 0;         // 현재 페이지 마지막 글번호
		int number = 0;      // 출력용 글번호 (db에서 중간에 글하나가 삭제되어도 보여주는 번호는 순차적으로 보여짐)
		String pageNum = ""; // 페이지 번호
		int currentPage = 0; // 현재페이지
				
		int pageCount = 0;   // 페이지 갯수
		int startPage = 0;   // 시작 페이지
		int endPage = 0;     // 마지막 페이지
		
		String id = (String) req.getSession().getAttribute("memId");	
		
		cnt = order.getOrderCnt(id);
		
		pageNum = req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";  // 첫페이지를 1페이지로 지정
		}
		
		currentPage  = Integer.parseInt(pageNum);
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		number = cnt - (currentPage - 1) * pageSize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("id", id);
		
		if(cnt > 0) {
			ArrayList<OrderVO> list = order.getOrderList2(map);
			model.addAttribute("list", list);
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;  
		if(currentPage % pageBlock == 0) {
			startPage -= pageBlock;
		}
		endPage = startPage + pageBlock - 1;  
		if(endPage > pageCount) {
			endPage = pageCount;
		}
				
		model.addAttribute("cnt", cnt);         // 글갯수
		model.addAttribute("number", number);   // 보여지는 번호
		model.addAttribute("pageNum", pageNum); // 화면상 표시되는 페이지 숫자
				
		if(cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}

	@Override
	public void updateOrder(HttpServletRequest req, Model model) {
		String id = (String) req.getSession().getAttribute("memId");
		String[] chk = req.getParameterValues("orderNum");		
		
		int updateCnt = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		for(int i=0; i<chk.length; i++) {
			map.put("orderNum", Integer.parseInt(chk[i]));
			updateCnt = order.cancelStatus(map);
		}
		model.addAttribute("updateCnt", updateCnt);
	}

	@Override
	public void refund(HttpServletRequest req, Model model) {
		String[] chk = req.getParameterValues("orderNum");		
		
		int updateCnt = 0;
		for(int i=0; i<chk.length; i++) {
			updateCnt = order.refundProduct(Integer.parseInt(chk[i]));
		}
		model.addAttribute("updateCnt", updateCnt);
	}

	@Override
	public void orderList1(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 10;    // 한페이지당 출력한 글 갯수
		int pageBlock = 5;   // 한 블럭당 페이지 갯수
				
		int cnt = 0;         // 글갯수
		int start = 0;       // 현재 페이지 시작 글번호
		int end = 0;         // 현재 페이지 마지막 글번호
		int number = 0;      // 출력용 글번호 (db에서 중간에 글하나가 삭제되어도 보여주는 번호는 순차적으로 보여짐)
		String pageNum = ""; // 페이지 번호
		int currentPage = 0; // 현재페이지
				
		int pageCount = 0;   // 페이지 갯수
		int startPage = 0;   // 시작 페이지
		int endPage = 0;     // 마지막 페이지					
		
		cnt = order.getCnt1();
		
		pageNum = req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";  // 첫페이지를 1페이지로 지정
		}
		
		currentPage  = Integer.parseInt(pageNum);
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		number = cnt - (currentPage - 1) * pageSize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		if(cnt > 0) {
			ArrayList<OrderVO> list = order.getOrderList3(map);
			model.addAttribute("list", list);
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;  
		if(currentPage % pageBlock == 0) {
			startPage -= pageBlock;
		}
		endPage = startPage + pageBlock - 1;  
		if(endPage > pageCount) {
			endPage = pageCount;
		}
				
		model.addAttribute("cnt", cnt);         // 글갯수
		model.addAttribute("number", number);   // 보여지는 번호
		model.addAttribute("pageNum", pageNum); // 화면상 표시되는 페이지 숫자
				
		if(cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}

	@Override
	public void orderList2(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 10;    // 한페이지당 출력한 글 갯수
		int pageBlock = 5;   // 한 블럭당 페이지 갯수
				
		int cnt = 0;         // 글갯수
		int start = 0;       // 현재 페이지 시작 글번호
		int end = 0;         // 현재 페이지 마지막 글번호
		int number = 0;      // 출력용 글번호 (db에서 중간에 글하나가 삭제되어도 보여주는 번호는 순차적으로 보여짐)
		String pageNum = ""; // 페이지 번호
		int currentPage = 0; // 현재페이지
				
		int pageCount = 0;   // 페이지 갯수
		int startPage = 0;   // 시작 페이지
		int endPage = 0;     // 마지막 페이지					
		
		cnt = order.getCnt2();
		
		pageNum = req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";  // 첫페이지를 1페이지로 지정
		}
		
		currentPage  = Integer.parseInt(pageNum);
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		number = cnt - (currentPage - 1) * pageSize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		if(cnt > 0) {
			ArrayList<OrderVO> list = order.getOrderList4(map);
			model.addAttribute("list", list);
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;  
		if(currentPage % pageBlock == 0) {
			startPage -= pageBlock;
		}
		endPage = startPage + pageBlock - 1;  
		if(endPage > pageCount) {
			endPage = pageCount;
		}
				
		model.addAttribute("cnt", cnt);         // 글갯수
		model.addAttribute("number", number);   // 보여지는 번호
		model.addAttribute("pageNum", pageNum); // 화면상 표시되는 페이지 숫자
				
		if(cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}

	@Override
	public void orderList3(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 10;    // 한페이지당 출력한 글 갯수
		int pageBlock = 5;   // 한 블럭당 페이지 갯수
				
		int cnt = 0;         // 글갯수
		int start = 0;       // 현재 페이지 시작 글번호
		int end = 0;         // 현재 페이지 마지막 글번호
		int number = 0;      // 출력용 글번호 (db에서 중간에 글하나가 삭제되어도 보여주는 번호는 순차적으로 보여짐)
		String pageNum = ""; // 페이지 번호
		int currentPage = 0; // 현재페이지
				
		int pageCount = 0;   // 페이지 갯수
		int startPage = 0;   // 시작 페이지
		int endPage = 0;     // 마지막 페이지					
		
		cnt = order.getCnt3();
		
		pageNum = req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";  // 첫페이지를 1페이지로 지정
		}
		
		currentPage  = Integer.parseInt(pageNum);
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		number = cnt - (currentPage - 1) * pageSize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		if(cnt > 0) {
			ArrayList<OrderVO> list = order.getOrderList5(map);
			model.addAttribute("list", list);
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;  
		if(currentPage % pageBlock == 0) {
			startPage -= pageBlock;
		}
		endPage = startPage + pageBlock - 1;  
		if(endPage > pageCount) {
			endPage = pageCount;
		}
				
		model.addAttribute("cnt", cnt);         // 글갯수
		model.addAttribute("number", number);   // 보여지는 번호
		model.addAttribute("pageNum", pageNum); // 화면상 표시되는 페이지 숫자
				
		if(cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}

	@Override
	public void orderList4(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 10;    // 한페이지당 출력한 글 갯수
		int pageBlock = 5;   // 한 블럭당 페이지 갯수
				
		int cnt = 0;         // 글갯수
		int start = 0;       // 현재 페이지 시작 글번호
		int end = 0;         // 현재 페이지 마지막 글번호
		int number = 0;      // 출력용 글번호 (db에서 중간에 글하나가 삭제되어도 보여주는 번호는 순차적으로 보여짐)
		String pageNum = ""; // 페이지 번호
		int currentPage = 0; // 현재페이지
				
		int pageCount = 0;   // 페이지 갯수
		int startPage = 0;   // 시작 페이지
		int endPage = 0;     // 마지막 페이지					
		
		cnt = order.getCnt4();
		
		pageNum = req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";  // 첫페이지를 1페이지로 지정
		}
		
		currentPage  = Integer.parseInt(pageNum);
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		number = cnt - (currentPage - 1) * pageSize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		if(cnt > 0) {
			ArrayList<OrderVO> list = order.getOrderList6(map);
			model.addAttribute("list", list);
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;  
		if(currentPage % pageBlock == 0) {
			startPage -= pageBlock;
		}
		endPage = startPage + pageBlock - 1;  
		if(endPage > pageCount) {
			endPage = pageCount;
		}
				
		model.addAttribute("cnt", cnt);         // 글갯수
		model.addAttribute("number", number);   // 보여지는 번호
		model.addAttribute("pageNum", pageNum); // 화면상 표시되는 페이지 숫자
				
		if(cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}

	@Override
	public void orderList5(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 10;    // 한페이지당 출력한 글 갯수
		int pageBlock = 5;   // 한 블럭당 페이지 갯수
				
		int cnt = 0;         // 글갯수
		int start = 0;       // 현재 페이지 시작 글번호
		int end = 0;         // 현재 페이지 마지막 글번호
		int number = 0;      // 출력용 글번호 (db에서 중간에 글하나가 삭제되어도 보여주는 번호는 순차적으로 보여짐)
		String pageNum = ""; // 페이지 번호
		int currentPage = 0; // 현재페이지
				
		int pageCount = 0;   // 페이지 갯수
		int startPage = 0;   // 시작 페이지
		int endPage = 0;     // 마지막 페이지			
		
		cnt = order.getCnt5();
		
		pageNum = req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";  // 첫페이지를 1페이지로 지정
		}
		
		currentPage  = Integer.parseInt(pageNum);
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		number = cnt - (currentPage - 1) * pageSize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		if(cnt > 0) {
			ArrayList<OrderVO> list = order.getOrderList7(map);
			model.addAttribute("list", list);
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;  
		if(currentPage % pageBlock == 0) {
			startPage -= pageBlock;
		}
		endPage = startPage + pageBlock - 1;  
		if(endPage > pageCount) {
			endPage = pageCount;
		}
				
		model.addAttribute("cnt", cnt);         // 글갯수
		model.addAttribute("number", number);   // 보여지는 번호
		model.addAttribute("pageNum", pageNum); // 화면상 표시되는 페이지 숫자
				
		if(cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}

	@Override
	public void orderList6(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 10;    // 한페이지당 출력한 글 갯수
		int pageBlock = 5;   // 한 블럭당 페이지 갯수
				
		int cnt = 0;         // 글갯수
		int start = 0;       // 현재 페이지 시작 글번호
		int end = 0;         // 현재 페이지 마지막 글번호
		int number = 0;      // 출력용 글번호 (db에서 중간에 글하나가 삭제되어도 보여주는 번호는 순차적으로 보여짐)
		String pageNum = ""; // 페이지 번호
		int currentPage = 0; // 현재페이지
				
		int pageCount = 0;   // 페이지 갯수
		int startPage = 0;   // 시작 페이지
		int endPage = 0;     // 마지막 페이지			
		
		cnt = order.getCnt6();
		
		pageNum = req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";  // 첫페이지를 1페이지로 지정
		}
		
		currentPage  = Integer.parseInt(pageNum);
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		number = cnt - (currentPage - 1) * pageSize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		if(cnt > 0) {
			ArrayList<OrderVO> list = order.getOrderList8(map);
			model.addAttribute("list", list);
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;  
		if(currentPage % pageBlock == 0) {
			startPage -= pageBlock;
		}
		endPage = startPage + pageBlock - 1;  
		if(endPage > pageCount) {
			endPage = pageCount;
		}
				
		model.addAttribute("cnt", cnt);         // 글갯수
		model.addAttribute("number", number);   // 보여지는 번호
		model.addAttribute("pageNum", pageNum); // 화면상 표시되는 페이지 숫자
				
		if(cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}

	@Override
	public void confirmOrder(HttpServletRequest req, Model model) {
		String[] chk = req.getParameterValues("orderNum");		
		
		int updateCnt = 0;
		for(int i=0; i<chk.length; i++) {
			updateCnt = order.confirmOrder(Integer.parseInt(chk[i]));
		}
		model.addAttribute("updateCnt", updateCnt);
	}

	@Override
	public void deleteOrder(HttpServletRequest req, Model model) {
		String[] chk = req.getParameterValues("orderNum");		
		
		int deleteCnt = 0;
		for(int i=0; i<chk.length; i++) {
			deleteCnt = order.deletePro(Integer.parseInt(chk[i]));
		}
		model.addAttribute("deleteCnt", deleteCnt);
	}

	@Override
	public void confirmDelivery(HttpServletRequest req, Model model) {
		String[] chk = req.getParameterValues("orderNum");		
		
		int updateCnt = 0;
		for(int i=0; i<chk.length; i++) {
			updateCnt = order.confirmDelivery(Integer.parseInt(chk[i]));
		}
		model.addAttribute("updateCnt", updateCnt);
	}

	@Override
	public void confirmRefund(HttpServletRequest req, Model model) {
		String[] chk = req.getParameterValues("orderNum");
				
		int updateCnt = 0;
		for(int i=0; i<chk.length; i++) {
			updateCnt = order.refundPro(Integer.parseInt(chk[i]));
		}
		model.addAttribute("updateCnt", updateCnt);
	}

	@Override
	public void rejectRefund(HttpServletRequest req, Model model) {
		String[] chk = req.getParameterValues("orderNum");		
		
		int updateCnt = 0;
		for(int i=0; i<chk.length; i++) {
			updateCnt = order.rejectRefund(Integer.parseInt(chk[i]));
		}
		model.addAttribute("updateCnt", updateCnt);
	}

	@Override
	public void closingTotal(HttpServletRequest req, Model model) {
		JSONArray jsonArray = new JSONArray();
		JSONArray colNameArray = new JSONArray(); // 컬 타이틀 설정
		colNameArray.add("month");
		colNameArray.add("매출액");
		jsonArray.add(colNameArray);
				
		// dao 호출
		ArrayList<TotalVO> list = order.total();
		
		for(TotalVO vo : list) {
			JSONArray rowArray = new JSONArray();
			rowArray.add(vo.getMonth());
			rowArray.add(vo.getSum());
			jsonArray.add(rowArray);
		}
		System.out.println(jsonArray);
						
		model.addAttribute("jsonArray", jsonArray);
	}				
}
