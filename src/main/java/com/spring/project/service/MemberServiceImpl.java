package com.spring.project.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.project.persistence.MemberDAO;
import com.spring.project.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO dao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;  // 비밀번호 암호화 객체	
	
	// 아이디 중복확인
	@Override
	public void confirmId(HttpServletRequest req, Model model) {
		 String id = req.getParameter("id");		  
		 int cnt = dao.idCheck(id); // strId = 화면으로부터 입력받은 값		 
		 model.addAttribute("selectCnt", cnt); 
		 model.addAttribute("id", id);		 
	}
	
	// 회원가입 처리
	@Override
	public void signInPro(HttpServletRequest req, Model model) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("id", req.getParameter("id")); 
		String encryptPassword = passwordEncoder.encode(req.getParameter("pwd")); 
		map.put("pwd", encryptPassword); 
		map.put("jumin1", req.getParameter("jumin1"));
		map.put("jumin2", req.getParameter("jumin2")); 
		map.put("name", req.getParameter("name"));
		map.put("address1", req.getParameter("address1")); 
		map.put("address2", req.getParameter("address2")); 
		map.put("address3", req.getParameter("address3")); 
		map.put("phone", req.getParameter("phone1") + "-" + req.getParameter("phone2") + "-" + req.getParameter("phone3"));
		map.put("email", req.getParameter("email1") + "@" + req.getParameter("email2")); 
		map.put("reg_date", new Timestamp(System.currentTimeMillis()));		

		int insertCnt = dao.insertMember(map);
		
		model.addAttribute("insertCnt", insertCnt);
	}
	
	// 회원탈퇴 처리
	public void deletePro(HttpServletRequest req, Model model) {
		String strPwd = req.getParameter("pwd"); 
		String id = (String) req.getSession().getAttribute("memId");

		int deleteCnt = 0;
		
		String dataPwd = dao.loadPwd(id);
		
		if(passwordEncoder.matches(strPwd, dataPwd)) { 
			deleteCnt = dao.deleteMember(id);
		} 
		req.setAttribute("deleteCnt", deleteCnt); 
	}

	// 회원정보 불러오기
	@Override 
	public void modifyView(HttpServletRequest req, Model model) { 
		String id = (String) req.getSession().getAttribute("memId"); 
		String strPwd = req.getParameter("pwd");
		
		String dataPwd = dao.loadPwd(id);
		
		int selectCnt = 0;
		MemberVO vo = null;
		if(passwordEncoder.matches(strPwd, dataPwd)) { 
			selectCnt = 1;
			vo = dao.getMemberInfo(id);
		} 
		model.addAttribute("vo", vo);
		model.addAttribute("selectCnt", selectCnt);
	}

	// 회원정보 수정처리
	@Override
	public void modifyPro(HttpServletRequest req, Model model) {
		String id = (String) req.getSession().getAttribute("memId");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		String encryptPassword = passwordEncoder.encode(req.getParameter("pwd"));
		vo.setPwd(encryptPassword);
		vo.setAddress1(req.getParameter("address1"));
		vo.setAddress2(req.getParameter("address2"));
		vo.setAddress3(req.getParameter("address3"));
		
		String phone1 = req.getParameter("phone1");
		String phone2 = req.getParameter("phone2");
		String phone3 = req.getParameter("phone3");
		vo.setPhone(phone1 + "-" + phone2 + "-" + phone3);
		
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		vo.setEmail(email1 + "@" + email2);
		
		int modifyCnt = dao.modifyMember(vo);
		
		req.setAttribute("modifyCnt", modifyCnt);
	}

	@Override
	public void findId(HttpServletRequest req, Model model) {
		String name = req.getParameter("name");
		String jumin1 = req.getParameter("jumin1");
		String jumin2 = req.getParameter("jumin2");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("jumin1", jumin1);
		map.put("jumin2", jumin2);
		
		String id = null;		
		id = dao.getMemberID(map);
		
		model.addAttribute("id", id);
	}
	
	// 이메일 인증

	@Override 
	public void emailChk(HttpServletRequest req, Model model) {
        String email = req.getParameter("email");
        String id = req.getParameter("id");
		String name = req.getParameter("name");
		String jumin1 = req.getParameter("jumin1");
		String jumin2 = req.getParameter("jumin2");
		
        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        
        for(int i = 0; i < 6; i++) {
            int rIndex = rnd.nextInt(2);
            switch(rIndex) {
            case 0:
                // A-Z
                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                break;
            case 1:
                // 0-9
                temp.append((rnd.nextInt(10)));
                break;
            }
        }
        
        String ranPwd = temp.toString();// StringBuffer 형식인 Key를 String으로 변환        
        System.out.println("ranPwd : " + ranPwd);                
        dao.sendmail(email, ranPwd);
        
        // 비밀번호 암호화
        String encryptPassword = passwordEncoder.encode(ranPwd);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("ranPwd", encryptPassword);
		map.put("name", name);
		map.put("jumin1", jumin1);
		map.put("jumin2", jumin2);
        dao.changePwd(map);
        
        model.addAttribute("selectCnt", 1); 
	}

	@Override
	public void memberList(HttpServletRequest req, Model model) {
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

		// 게시글 갯수
		cnt = dao.getMemberCnt();
				
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
			// 게시글 목록 조회
			ArrayList<MemberVO> list = dao.getMemberList(map);
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
	public void deleteMember(HttpServletRequest req, Model model) {
		String[] chk = req.getParameterValues("checkbox");
		int deleteCnt = 0;
		
		for(int i=0; i<chk.length; i++) {
			deleteCnt = dao.deleteMember(chk[i]);
			System.out.println(chk[i] + "삭제완료");
		}
		
		model.addAttribute("deleteCnt", deleteCnt);
	}		

}
