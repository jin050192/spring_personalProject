package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.Map;

import com.spring.project.vo.NoticeVO;

public interface NoticeDAO {
	// 게시글 갯수 구하기
	public int getArticleCnt();
	
	// 게시글 목록 불러오기
	public ArrayList<NoticeVO> getArticleList(Map<String, Object> map);
	
	// 조회수 증가
	public void addReadCnt(int num);
	
	// 게시글 상세 페이지
	public NoticeVO getArticle(int num);
	
	// 게시글 수정 처리
	public int updateBoard(NoticeVO vo);
	
	// 글쓰기 처리
	public int insertBoard(NoticeVO vo);
	
	// 글삭제 처리
	public int deleteBoard(int num);
}
