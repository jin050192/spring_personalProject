package com.spring.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.project.persistence.ProductDAO;
import com.spring.project.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;
	
	@Override
	public void productList1(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 8;    // 한페이지당 출력한 글 갯수
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
		
		cnt = dao.getProCnt1();
		
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
			List<ProductVO> list = dao.productList1(map);
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
	public void productList2(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 8;    // 한페이지당 출력한 글 갯수
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
		
		cnt = dao.getProCnt2();
		
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
			List<ProductVO> list = dao.productList2(map);
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
	public void productList3(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 8;    // 한페이지당 출력한 글 갯수
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
		
		cnt = dao.getProCnt3();
		
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
			List<ProductVO> list = dao.productList3(map);
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
	public void productList4(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 8;    // 한페이지당 출력한 글 갯수
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
		
		cnt = dao.getProCnt4();
		
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
			List<ProductVO> list = dao.productList4(map);
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
	public void productList5(HttpServletRequest req, Model model) {
		// 페이징
		int pageSize = 8;    // 한페이지당 출력한 글 갯수
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
		
		cnt = dao.getProCnt5();
		
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
			List<ProductVO> list = dao.productList5(map);
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
	public void productInfo(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		
		ProductVO vo = new ProductVO();		
		// 조회수 증가
		dao.addReadCnt(num);		
		// 상세페이지 조회
		vo = dao.getInfo(num);		
		model.addAttribute("dto", vo);
	}

	@Override
	public void bestItems(HttpServletRequest req, Model model) {				
		// 베스트 아이템 탑4
		List<ProductVO> list = dao.bestItems();
		model.addAttribute("best", list);
		
		// 뉴 아이템 탑4
		List<ProductVO> newList = dao.newItems();
		model.addAttribute("newList", newList);
	}

	@Override
	public void productList(HttpServletRequest req, Model model) {
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
		
		cnt = dao.getProCnt();
		
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
			List<ProductVO> list = dao.productList(map);
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
    public void inventory_add(MultipartHttpServletRequest req, Model model) {
        MultipartFile file = req.getFile("img");
        
        String saveDir = req.getSession().getServletContext().getRealPath("/resources/images/");
        
        String realDir="D:\\Dev65\\workspace\\spring_project\\src\\main\\webapp\\resources\\images\\";
        
        try {
            file.transferTo(new File(saveDir+file.getOriginalFilename()));
            
            FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());
            
            int data = 0;
            
            while((data = fis.read()) != -1) {
                fos.write(data);
            }
            fis.close();
            fos.close();
            
            String img = file.getOriginalFilename();
            
            ProductVO vo = new ProductVO();
            vo.setSort(Integer.parseInt(req.getParameter("sort")));
            vo.setImg(img);
            vo.setName(req.getParameter("name"));
            vo.setPrice(Integer.parseInt(req.getParameter("price")));
            vo.setCount(Integer.parseInt(req.getParameter("count")));
            vo.setContent(req.getParameter("Content"));
            vo.setReg_date(new Timestamp(System.currentTimeMillis()));
            
            int insertCnt = dao.putProduct(vo);
            
            model.addAttribute("insertCnt", insertCnt);
            
        } catch(IOException e) {
            e.printStackTrace();
        }       
    }

	@Override
	public void getProductInfo(HttpServletRequest req, Model model) {
		String strNum = req.getParameter("num");
		if(strNum != null) {
			int num = Integer.parseInt(req.getParameter("num"));			
			ProductVO vo = dao.getInfo(num);			
			model.addAttribute("dto", vo);
		}		
	}

	@Override
	public void updatePro(HttpServletRequest req, Model model) {
		ProductVO vo = new ProductVO();
		int num = Integer.parseInt(req.getParameter("num"));				
		String img = req.getParameter("img");	
		vo.setNum(num);
		vo.setName(req.getParameter("name"));
		vo.setPrice(Integer.parseInt(req.getParameter("price")));
		vo.setCount(Integer.parseInt(req.getParameter("count")));
		vo.setSort(Integer.parseInt(req.getParameter("sort")));
		vo.setContent(req.getParameter("content"));		
		
		int updateCnt = dao.updateProduct(vo);
		
		model.addAttribute("updateCnt", updateCnt);
		
	}

	@Override
	public void deletePro(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));				
		int delectCnt = dao.deleteProduct(num);		
		model.addAttribute("delectCnt", delectCnt);
		model.addAttribute("num", num);
	}
    
    
}
