package com.spring.project.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.project.persistence.BoardDAO;
import com.spring.project.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO dao;
	
	@Override
	public void boardList(HttpServletRequest req, Model model) {
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
		cnt = dao.getArticleCnt();
		
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
			ArrayList<BoardVO> list = dao.getArticleList(map);
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
	public void contentForm(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));		
		
		//  조회수 증가
		dao.addReadCnt(num);
		
		// 상세페이지 조회
		BoardVO vo = dao.getArticle(num);

		model.addAttribute("dto", vo);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("number", number);
	}

	@Override
	public void modifyView(HttpServletRequest req, Model model) {		
		// 3단계. 화면으로부터 입력받은 값(get방식)을 받아온다.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String pwd = req.getParameter("pwd");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pwd", pwd);
		// 5-1단계. 비밀번호 인증
		int selectCnt = dao.numPwdCheck(map);
		// 5-2단계. 비밀번호 일치하면, 해당글 1건을 읽어서 vo에 담아서 불러오기
		BoardVO vo = null;
		if(selectCnt == 1) {
			vo = dao.getArticle(num);
		}
		// 6단계. request나 session에 처리결과를 저장
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("dto", vo);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
	}

	@Override
	public void modifyPro(HttpServletRequest req, Model model) {
		BoardVO vo = new BoardVO();
		
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int num = Integer.parseInt(req.getParameter("num"));
		
		vo.setNum(num);		
		vo.setSubject(req.getParameter("subject"));
		vo.setContent(req.getParameter("content"));		
		
		int updateCnt = dao.updateBoard(vo);
		
		model.addAttribute("updateCnt", updateCnt);
		model.addAttribute("pageNum", pageNum);		
	}

	@Override
	public void writeForm(HttpServletRequest req, Model model) {
		// 제목글(답변글이 아닌 경우)
		int num = 0;
		int pageNum = 0;
		int ref = 0;
		int ref_step = 0;
		int ref_level = 0;

		// 답변글 작성시
		if(req.getParameter("num") != null) {					
			num = Integer.parseInt(req.getParameter("num"));			
			ref = Integer.parseInt(req.getParameter("ref"));
			ref_step = Integer.parseInt(req.getParameter("ref_step"));
			ref_level = Integer.parseInt(req.getParameter("ref_level"));
		}
		pageNum = Integer.parseInt(req.getParameter("pageNum"));

		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("ref", ref);
		model.addAttribute("ref_step", ref_step);
		model.addAttribute("ref_level", ref_level);		
	}

	@Override
	public void writePro(HttpServletRequest req, Model model) {
		BoardVO vo = new BoardVO();
		
		// 히든값 담기
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		vo.setNum(Integer.parseInt(req.getParameter("num")));
		vo.setRef(Integer.parseInt(req.getParameter("ref")));
		vo.setRef_step(Integer.parseInt(req.getParameter("ref_step")));
		vo.setRef_level(Integer.parseInt(req.getParameter("ref_level")));		
		// input값 담기		
		vo.setPwd(req.getParameter("pwd"));
		vo.setId(req.getParameter("id"));
		vo.setSubject(req.getParameter("subject"));
		vo.setContent(req.getParameter("content"));
		// reg_date에 null로 insert시킬경우, default에 sysdate로 설정되어 있으면 sysdate로 insert됨
		vo.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		int insertCnt = dao.insertBoard(vo);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("insertCnt", insertCnt);
	}

	@Override
	public void deletePro(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String pwd = req.getParameter("pwd");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pwd", pwd);		
		// 비밀번호 인증
		int selectCnt = dao.numPwdCheck(map);
		// 인증되면 삭제진행
		int delectCnt = 0;
		if(selectCnt == 1) {
			delectCnt = dao.deleteBoard(num);
		}
				
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("delectCnt", delectCnt);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
	}

	@Override
	public void myboardList(HttpServletRequest req, Model model) {
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
		
		// 받은값 불러오기
		String id = (String) req.getSession().getAttribute("memId");
		
		// 게시글 갯수
		cnt = dao.getMyCnt(id);
		
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
			// 게시글 목록 조회
			ArrayList<BoardVO> list = dao.getMyboardList(map);
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
}
