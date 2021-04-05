package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.Map;

import com.spring.project.vo.BoardVO;

public interface BoardDAO {
	// 게시글 갯수 구하기
	public int getArticleCnt();
	
	// 게시글 목록 불러오기
	public ArrayList<BoardVO> getArticleList(Map<String, Object> map);
	
	// 조회수 증가
	public void addReadCnt(int num);
	
	// 게시글 상세 페이지
	public BoardVO getArticle(int num);
	
	// 비밀번호 확인
	public int numPwdCheck(Map<String, Object> map);
	
	// 게시글 수정 처리
	public int updateBoard(BoardVO vo);
	
	// 글쓰기 처리1
	public int getMaxNum();
	
	// 글쓰기 처리2
	public void updateReply(BoardVO vo);
	
	// 글쓰기 처리3
	public int insertBoard(BoardVO vo);
	
	// 글삭제2. 답글이 존재하는 경우
	public int checkReply(Map<String, Object> map);
	
	// 글삭제3
	public int deleteAll(Map<String, Object> map);
	
	// 글삭제4
	public int deleteAll2(Map<String, Object> map);
	
	// 글삭제 처리
	public int deleteBoard(int num);
	
	// 내가 쓴글 갯수 확인
	public int getMyCnt(String id);
	
	// 내가 쓴글 리스트 불러오기
	public ArrayList<BoardVO> getMyboardList(Map<String, Object> map);
	
}
