package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	SqlSession sqlSession;
		
	// 컨넥션풀 객체를 보관
	DataSource dataSource;
	

	@Override
	public int getArticleCnt() {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.getArticleCnt();
	}

	@Override
	public ArrayList<BoardVO> getArticleList(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.getArticleList(map);
	}

	@Override
	public void addReadCnt(int num) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		dao.addReadCnt(num);
	}

	@Override
	public BoardVO getArticle(int num) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.getArticle(num);
	}

	@Override
	public int numPwdCheck(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.numPwdCheck(map);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.updateBoard(vo);
	}

	// 글쓰기 - 1. 답변글이 아닌 경우(제목글인 경우)
	@Override
	public int getMaxNum() {
		// 최신글부터 가져온다.
		// sql = "select max(num) as maxNum from mvc_board_tbl";
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.getMaxNum();
	}
	
	// 2. 답변글의 경우
	@Override
	public void updateReply(BoardVO vo) {
		// 삽입할 글보다 아래쪽 글들이 한줄씩 밀려내려간다. 즉 ref_step(행)이 1씩 증가... ref_step을 update
		// sql = "update mvc_board_tbl set ref_step=ref_step+1 where ref=? and ref_step > ?";	
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		dao.updateReply(vo);
	}
	
	// 3. 글쓰기 처리
	@Override
	public int insertBoard(BoardVO vo) {
		int num = vo.getNum();
		int ref = vo.getRef();
		int ref_step = vo.getRef_step();
		int ref_level = vo.getRef_level();
								
		if(num == 0) { // 답변글이 아닌 경우(제목글인 경우)
			int cnt = getArticleCnt();
			// 최신글부터 가져온다.
			// 1.getMaxNum()				
			// 첫글이 아닌 경우 : num과 ref는 동일
			if(cnt > 0) {
				ref = getMaxNum() + 1;
			} else {
				// 첫글인 경우
				ref = 1;
			}
			ref_step = 0;
			ref_level = 0;
		} else {
			// 2. 답변글의 경우
			// 삽입할 글보다 아래쪽 글들이 한줄씩 밀려내려간다. 즉 ref_step(행)이 1씩 증가... ref_step을 update			
			updateReply(vo);
			
			// 현재 내 답변글
			ref_step++;
			ref_level++;
		}
		
		// 3. insert
		vo.setRef(ref);
		vo.setRef_step(ref_step);
		vo.setRef_level(ref_level);
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.insertBoard(vo);
	}

	// 글삭제2. 답글이 존재하는 경우
	@Override
	public int checkReply(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);		
		return dao.checkReply(map);
	}
		
	// 글삭제3
	@Override
	public int deleteAll(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.deleteAll(map);
	}
	
	// 글삭제4
	public int deleteAll2(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.deleteAll2(map);
	}
	@Override
	public int deleteBoard(int num) {
		int deleteCnt = 0;
		int ref = 0;
		int ref_step = 0;
		int ref_level = 0;
		int ref_step_a = 0;
		
		// 글삭제1. 상세페이지 조회
		BoardVO vo = new BoardVO();
		vo = getArticle(num);
		Map<String, Object> map = new HashMap<String, Object>();
		ref = vo.getRef();
		ref_step = vo.getRef_step();
		ref_level = vo.getRef_level();
		map.put("ref", ref);
		map.put("ref_step", ref_step);
		map.put("ref_level", ref_level);
		
		// 넘겨받은 num에 해당하는 키값이 존재하면
		if(vo.getNum() != 0) {
			// 2. 답글이 존재하는 경우
			int replyCnt = checkReply(map);						
			// 3. 답글이 존재하는 경우 삭제
			if(replyCnt != 0) {
				ref_step_a = vo.getRef_step();
				map.put("ref_step_a", ref_step_a);
				deleteCnt = deleteAll(map);
			} else {
				// 4. 답글이 존재하지 않는 경우
				deleteCnt = deleteAll2(map);
			}
		}		
		return deleteCnt;
	}

	@Override
	public int getMyCnt(String id) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.getMyCnt(id);
	}

	@Override
	public ArrayList<BoardVO> getMyboardList(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.getMyboardList(map);
	}		
}
