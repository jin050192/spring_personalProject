package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.vo.NoticeVO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	@Autowired
	SqlSession sqlSession;	
	
	@Override
	public int getArticleCnt() {
		NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
		return dao.getArticleCnt();
	}

	@Override
	public ArrayList<NoticeVO> getArticleList(Map<String, Object> map) {
		NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
		return dao.getArticleList(map);
	}

	@Override
	public void addReadCnt(int num) {
		NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
		dao.addReadCnt(num);				
	}

	@Override
	public NoticeVO getArticle(int num) {
		NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
		return dao.getArticle(num);
	}

	@Override
	public int updateBoard(NoticeVO vo) {
		NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
		return dao.updateBoard(vo);
	}

	@Override
	public int insertBoard(NoticeVO vo) {
		NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
		return dao.insertBoard(vo);
	}

	@Override
	public int deleteBoard(int num) {
		NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
		return dao.deleteBoard(num);
	}	
}
