package com.spring.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.project.service.BoardServiceImpl;
import com.spring.project.service.CartServiceImpl;
import com.spring.project.service.MemberServiceImpl;
import com.spring.project.service.NoticeServiceImpl;
import com.spring.project.service.OrderServiceImpl;
import com.spring.project.service.ProductServiceImpl;


@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	MemberServiceImpl member;
	
	@Autowired
	ProductServiceImpl product;
	
	@Autowired
	BoardServiceImpl board;
	
	@Autowired
	NoticeServiceImpl notice;
	
	@Autowired
	CartServiceImpl cart;
	
	@Autowired
	OrderServiceImpl order;
	
	// main 페이지 이동
	@RequestMapping("home.do")
	public String home(HttpServletRequest req, Model model) {
		logger.info("url ==> home");
		product.bestItems(req, model);
		return "home";
	}
	
	// 로그인 페이지로 이동
	@RequestMapping("login.do")
	public String login() {
		logger.info("url ==> login");		
		return "login";
	}
	
	// 로그인처리
	@RequestMapping("/user/loginPro.do")
	public String loginPro() {
		logger.info("url ==> loginPro");
		return "user/loginPro";
	}
	
	// 로그아웃 처리
	@RequestMapping("/user/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();		
		return "user/logout";
	}
	
	// 회원가입 페이지 이동
	@RequestMapping("signInForm.do")
	public String join() {
		logger.info("url ==> signInForm");		
		return "signInForm";
	}
	
	// 아이디 중복확인
	@RequestMapping("confirmId.do")
	public String confirmId(HttpServletRequest req, Model model) {
		member.confirmId(req, model);
		return "confirmId";
	}
	
	// 회원가입 처리
	@RequestMapping("signInPro.do")
	public String insertMember(HttpServletRequest req, Model model) {
		logger.info("url ==> insertMember");
		member.signInPro(req, model);
		return "signInPro";
	}
	
	// 마이페이지 이동
	@RequestMapping("/user/mypage.do")
	public String mypage() {
		logger.info("url ==> mypage");
		return "user/mypage";
	}
	
	// 회원탈퇴 페이지 이동
	@RequestMapping("/user/deleteForm.do")
	public String deleteForm() {
		logger.info("url ==> deleteForm");
		return "user/deleteForm";
	}
	
	// 회원탈퇴 처리
	@RequestMapping("/user/deletePro.do")
	public String deletePro(HttpServletRequest req, Model model) {
		logger.info("url ==> deletePro");
		member.deletePro(req, model);
		return "user/deletePro";
	}
	
	// 회원수정 페이지 이동
	@RequestMapping("/user/modifyForm.do")
	public String modifyForm() {
		logger.info("url ==> modifyForm");
		return "user/modifyForm";
	}
	
	// 회원수정 정보 불러오기
	@RequestMapping("/user/modifyView.do")
	public String modifyView(HttpServletRequest req, Model model) {
		logger.info("url ==> modifyView");
		member.modifyView(req, model);
		return "user/modifyView";
	}
	
	// 회원정보 수정 처리
	@RequestMapping("/user/modifyPro.do")
	public String modifyPro(HttpServletRequest req, Model model) {
		logger.info("url ==> modifyPro");
		member.modifyPro(req, model);
		return "user/modifyPro";
	}
	
	// 아이디 찾기 페이지 이동
	@RequestMapping("findId.do")
	public String findId() {
		logger.info("url ==> findId");
		return "findId";
	}
	
	// 아이디 정보 뿌리기
	@RequestMapping("findIdPro.do")
	public String findIdPro(HttpServletRequest req, Model model) {
		logger.info("url ==> findIdPro");
		member.findId(req, model);
		return "findIdPro";
	}
	
	// 비밀번호 찾기 페이지 이동
	@RequestMapping("findPwd.do")
	public String findPwd() {
		logger.info("url ==> findPwd");
		return "findPwd";
	}
	
    // 비밀번호찾기 이메일 코드 보내기
    @RequestMapping(value="findPwdPro.do", method=RequestMethod.GET)
    public String findId(HttpServletRequest req, Model model) {
    	logger.info("url ==> findPwdPro");      
        member.emailChk(req, model);       
        return "findPwdPro";
    }
    
    //------------------------------관리자------------------------------
	// 관리자 로그인 페이지로 이동
	@RequestMapping("host_login")
	public String host_login() {
		logger.info("url ==> host_login");		
		return "host_login";
	}
	
	// 관리자 로그인처리
	@RequestMapping("/admin/loginPro")
	public String host_loginPro() {
		logger.info("url ==> admin/loginPro");
		return "admin/loginPro";
	}
	
	// 관리자 로그아웃 처리
	@RequestMapping("/admin/logout")
	public String adminLogout(HttpSession session) {
		session.invalidate();		
		return "admin/logout";
	}
	
	// 관리자 재고 상품 리스트
	@RequestMapping("/admin/stockList")
	public String stockList(HttpServletRequest req, Model model) {
		logger.info("url ==> admin/stockList");
		product.productList(req, model);
		return "admin/stockList";
	}
	
	// 관리자 재고 상품 추가 페이지
	@RequestMapping("/admin/add")
	public String stockAdd(HttpServletRequest req, Model model) {
		logger.info("url ==> /admin/add");		
		return "admin/add";
	}
	
	// 상품 추가 처리
    @RequestMapping(value="/admin/addPro", method=RequestMethod.POST)
    public String addPro(MultipartHttpServletRequest req, Model model) {
        System.out.println("url ==> /admin/addPro");       
        product.inventory_add(req, model);       
        return "admin/addPro";
    }
    
	// 관리자 재고 상품 수정/삭제 페이지
	@RequestMapping("/admin/edit")
	public String stockEdit(HttpServletRequest req, Model model) {
		logger.info("url ==> /admin/edit");
		product.getProductInfo(req, model);
		return "admin/edit";
	}
	
	// 관리자 재고 상품 수정처리
	@RequestMapping("/admin/editPro")
	public String editPro(HttpServletRequest req, Model model) {
		logger.info("url ==> /admin/editPro");
		product.updatePro(req, model);
		return "admin/editPro";
	}
	
	// 관리자 재고 상품 삭제처리
	@RequestMapping("/admin/deleteProduct")
	public String deleteProduct(HttpServletRequest req, Model model) {
		logger.info("url ==> /admin/deleteProduct");
		product.deletePro(req, model);
		return "admin/deletePro";
	}
	
	// 관리자 회원 관리
	@RequestMapping("/admin/manageCus")
	public String manageCus(HttpServletRequest req, Model model) {
		logger.info("url ==> /admin/manageCus");
		member.memberList(req, model);
		return "admin/manageCus";
	}
	
	// 관리자 회원 강제탈퇴
	@RequestMapping("/admin/deleteMember")
	public String deleteMember(HttpServletRequest req, Model model) {
		logger.info("url ==> /admin/deleteMember");
		member.deleteMember(req, model);
		return "admin/deleteMem";
	}
	
	// 관리자 공지사항 게시판
	@RequestMapping("/admin/new&event")
	public String noticeList(HttpServletRequest req, Model model) {
		logger.info("url ==> /admin/new&event");
		notice.boardList(req, model);
		return "notice/new&event";
	}
	
	// 관리자 공지사항 게시판
	@RequestMapping("/admin/board")
	public String boardList(HttpServletRequest req, Model model) {
		logger.info("url ==> /admin/board");
		board.boardList(req, model);
		return "board/board";
	}
	//-----------------------------상품 관리----------------------------------
	// 상품리스트1
	@RequestMapping("sort1")
	public String sort1(HttpServletRequest req, Model model) {
		logger.info("url ==> sort1");
		product.productList1(req, model);
		return "sort1";
	}
	
	// 상품리스트2
	@RequestMapping("sort2")
	public String sort2(HttpServletRequest req, Model model) {
		logger.info("url ==> sort2");
		product.productList2(req, model);
		return "sort2";
	}
	
	// 상품리스트3
	@RequestMapping("sort3")
	public String sort3(HttpServletRequest req, Model model) {
		logger.info("url ==> sort3");
		product.productList1(req, model);
		return "sort3";
	}
	
	// 상품리스트4
	@RequestMapping("sort4")
	public String sort4(HttpServletRequest req, Model model) {
		logger.info("url ==> sort4");
		product.productList1(req, model);
		return "sort4";
	}
	
	// 상품리스트5
	@RequestMapping("sort5")
	public String sort5(HttpServletRequest req, Model model) {
		logger.info("url ==> sort5");
		product.productList2(req, model);
		return "sort5";
	}
	
	// 상품 정보
	@RequestMapping("productInfo")
	public String productInfo(HttpServletRequest req, Model model) {
		logger.info("url ==> productInfo");
		product.productInfo(req, model);
		return "productInfo";
	}
	
	//-----------------------------일반게시판--------------------------------
	// 게시판 리스트
	@RequestMapping("board")
	public String board(HttpServletRequest req, Model model) {
		logger.info("url ==> board");
		board.boardList(req, model);
		return "board/board";
	}
	
	// 게시글 정보
	@RequestMapping("content")
	public String content(HttpServletRequest req, Model model) {
		logger.info("url ==> content");
		board.contentForm(req, model);
		return "board/content";
	}
	
	// 게시글 정보 수정 비밀번호 인증 페이지
	@RequestMapping("modifyBoard")
	public String deleteBoard(HttpServletRequest req, Model model) {
		logger.info("url ==> modifyBoard");
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		return "board/modifyForm";
	}
	
	// 게시글 정보 수정폼
	@RequestMapping("modifyViewBoard")
	public String deleteBoardView(HttpServletRequest req, Model model) {
		logger.info("url ==> modifyView");
		board.modifyView(req, model);
		return "board/modifyView";
	}
	
	// 게시글 정보 수정처리
	@RequestMapping("modifyProBoard")
	public String modifyProBoard(HttpServletRequest req, Model model) {
		logger.info("url ==> modifyProBoard");
		board.modifyPro(req, model);
		return "board/modifyPro";
	}
	
	// 게시판 글작성 페이지
	@RequestMapping("writeForm")
	public String writeForm(HttpServletRequest req, Model model) {
		logger.info("url ==> writeForm");
		board.writeForm(req, model);
		return "board/writeForm";
	}
	
	// 게시판 글작성 처리
	@RequestMapping("writePro")
	public String writePro(HttpServletRequest req, Model model) {
		logger.info("url ==> writePro");
		board.writePro(req, model);
		return "board/writePro";
	}
	
	// 글삭제 비밀번호 인증페이지
	@RequestMapping("deleteBoardForm")
	public String deleteBoardForm(HttpServletRequest req, Model model) {
		logger.info("url ==> deleteBoardForm");
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		return "/board/deleteForm";
	}
	
	// 게시판 글삭제 처리
	@RequestMapping("deleteBoardPro")
	public String deleteBoardPro(HttpServletRequest req, Model model) {
		logger.info("url ==> deleteBoardPro");
		board.deletePro(req, model);
		return "board/deletePro";
	}
	
	// 내가 쓴글 게시판 리스트
	@RequestMapping("/user/myPage_board")
	public String myPage_board(HttpServletRequest req, Model model) {
		logger.info("url ==> myPage_board");
		board.myboardList(req, model);
		return "board/myPage_board";
	}
	
	//-----------------------공지사항 게시판------------------------------
	// 공지사항 리스트
	@RequestMapping("new&event")
	public String notice(HttpServletRequest req, Model model) {
		logger.info("url ==> new&event");
		notice.boardList(req, model);
		return "notice/new&event";
	}
	
	// 공지사항 게시글 정보
	@RequestMapping("notice_content")
	public String notice_content(HttpServletRequest req, Model model) {
		logger.info("url ==> notice_content");
		notice.contentForm(req, model);
		return "notice/content";
	}
	
	// 공지사항 게시글 정보 수정폼
	@RequestMapping("/admin/modifyFormNotice")
	public String modifyFormNotice(HttpServletRequest req, Model model) {
		logger.info("url ==> modifyFormNotice");
		notice.modifyView(req, model);
		return "notice/modifyForm";
	}
	
	// 공지사항게시글 정보 수정처리
	@RequestMapping("/admin/modifyProNotice")
	public String modifyProNotice(HttpServletRequest req, Model model) {
		logger.info("url ==> modifyProNotice");
		notice.modifyPro(req, model);
		return "notice/modifyPro";
	}
	
	// 공지사항 게시판 글작성 페이지
	@RequestMapping("/admin/writeFormNotice")
	public String writeFormNotice(HttpServletRequest req, Model model) {
		logger.info("url ==> writeFormNotice");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		model.addAttribute("pageNum", pageNum);
		return "notice/writeForm";
	}
	
	// 공지사항 게시판 글작성 처리
	@RequestMapping("/admin/writeProNotice")
	public String writeProNotice(HttpServletRequest req, Model model) {
		logger.info("url ==> writeProNotice");
		notice.writePro(req, model);
		return "notice/writePro";
	}
	
	// 공지사항 게시판 글삭제 처리
	@RequestMapping("/admin/deleteNoticePro")
	public String deleteNoticePro(HttpServletRequest req, Model model) {
		logger.info("url ==> deleteNoticePro");
		notice.deletePro(req, model);
		return "notice/deletePro";
	}
	
	// 승인 대기 주문 리스트
	@RequestMapping("/admin/orderList1")
	public String orderList1(HttpServletRequest req, Model model) {
		logger.info("url ==> orderList1");
		order.orderList1(req, model);
		return "admin/orderList1";
	}
	
	// 배송중 리스트
	@RequestMapping("/admin/orderList2")
	public String orderList2(HttpServletRequest req, Model model) {
		logger.info("url ==> orderList2");
		order.orderList2(req, model);
		return "admin/orderList2";
	}
	
	// 주문 취소 리스트
	@RequestMapping("/admin/orderList3")
	public String orderList3(HttpServletRequest req, Model model) {
		logger.info("url ==> orderList3");
		order.orderList3(req, model);
		return "admin/orderList3";
	}
	
	// 환불요청 리스트
	@RequestMapping("/admin/orderList4")
	public String orderList4(HttpServletRequest req, Model model) {
		logger.info("url ==> orderList4");
		order.orderList5(req, model);
		return "admin/orderList4";
	}
	
	// 배송완료 리스트
	@RequestMapping("/admin/orderList5")
	public String orderList5(HttpServletRequest req, Model model) {
		logger.info("url ==> orderList5");
		order.orderList4(req, model);
		return "admin/orderList5";
	}
	
	// 환불 승인 리스트
	@RequestMapping("/admin/orderList6")
	public String orderList6(HttpServletRequest req, Model model) {
		logger.info("url ==> orderList6");
		order.orderList6(req, model);
		return "admin/orderList6";
	}
	
	// 주문 승인 처리
	@RequestMapping("/admin/updatePro")
	public String updatePro(HttpServletRequest req, Model model) {
		logger.info("url ==> updatePro");
		order.confirmOrder(req, model);
		return "admin/updatePro";
	}
	
	// 배송처리
	@RequestMapping("/admin/deliveryPro")
	public String deliveryPro(HttpServletRequest req, Model model) {
		logger.info("url ==> deliveryPro");
		order.confirmDelivery(req, model);
		return "admin/deliveryPro";
	}
	
	// 주문 취소 처리
	@RequestMapping("/admin/deleteOrder")
	public String deleteOrderList(HttpServletRequest req, Model model) {
		logger.info("url ==> deleteOrder");
		order.deleteOrder(req, model);
		return "admin/deleteOrder";
	}
	
	// 환불 승인 처리
	@RequestMapping("/admin/confirmRefund")
	public String confirmRefund(HttpServletRequest req, Model model) {
		logger.info("url ==> confirmRefund");
		order.confirmRefund(req, model);
		return "admin/confirmRefund";
	}
	
	// 환불 거부 처리
	@RequestMapping("/admin/rejectRefund")
	public String rejectRefund(HttpServletRequest req, Model model) {
		logger.info("url ==> rejectRefund");
		order.rejectRefund(req, model);
		return "admin/rejectRefund";
	}
	
	// 결산
	@RequestMapping("/admin/closingAccount")
	public String closingAccount(HttpServletRequest req, Model model) {
		logger.info("url ==> closingAccount");
		order.closingTotal(req, model);
		return "admin/closingAccount";
	}
	//----------------------------장바구니-----------------------------------
	// 장바구니 리스트
	@RequestMapping("/user/cartList")
	public String cartList(HttpServletRequest req, Model model) {
		logger.info("url ==> cartList");
		cart.cartList(req, model);
		return "cart/cartList";
	}
	
	// 장바구니 상품 추가
	@RequestMapping("/user/cartAdd")
	public String cartAdd(HttpServletRequest req, Model model) {
		logger.info("url ==> cartAdd");
		cart.addCart(req, model);
		return "cart/cartAdd";
	}
	
	// 장바구니 상품 수량 변경
	@RequestMapping("/user/modifyCart")
	public String modifyCart(HttpServletRequest req, Model model) {
		logger.info("url ==> modifyCart");
		cart.updateCart(req, model);
		return "cart/modifyCart";
	}
	
	// 장바구니 상품 삭제
	@RequestMapping("/user/deleteCart")
	public String deleteCart(HttpServletRequest req, Model model) {
		logger.info("url ==> deleteCart");
		cart.deleteCart(req, model);
		return "cart/deleteCart";
	}
	
	//----------------------------주문-----------------------------------
	// 주문폼
	@RequestMapping("/user/orderForm")
	public String orderForm(HttpServletRequest req, Model model) {
		logger.info("url ==> orderForm");
		order.getOrderForm(req, model);
		return "order/orderForm";
	}
	
	// 카트 -> 주문
	@RequestMapping("/user/cartToOrder")
	public String cartToOrder(HttpServletRequest req, Model model) {
		logger.info("url ==> cartToOrder");
		order.cartToOrder(req, model);
		return "order/orderForm";
	}
	
	// 주문 진행
	@RequestMapping("/user/orderPro")
	public String orderPro(HttpServletRequest req, Model model) {
		logger.info("url ==> orderPro");
		order.orderPro(req, model);
		return "order/orderPro";
	}
	
	// 주문폼에서 상품주문 취소
	@RequestMapping("/user/deleteOrder")
	public String deleteOrder(HttpServletRequest req, Model model) {
		logger.info("url ==> deleteOrder");
		order.cancelOrder(req, model);
		return "user/mypage";
	}
	
	// 주문 리스트
	@RequestMapping("/user/orderList")
	public String orderList(HttpServletRequest req, Model model) {
		logger.info("url ==> orderList");
		order.orderList(req, model);
		return "order/orderList";
	}
	
	// 주문후 배송되기전 취소요청
	@RequestMapping("/user/cancelOrder")
	public String cancelOrder(HttpServletRequest req, Model model) {
		logger.info("url ==> cancelOrder");
		order.updateOrder(req, model);
		return "order/updatePro";
	}
	
	// 환불요청
	@RequestMapping("/user/refundPro")
	public String refundPro(HttpServletRequest req, Model model) {
		logger.info("url ==> refundPro");
		order.refund(req, model);
		return "order/refundPro";
	}
		
}
