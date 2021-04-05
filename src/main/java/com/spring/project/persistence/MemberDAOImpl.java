package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.spring.project.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	
	// 로그인시 아이디 비번 확인
	@Override
	public Map<String, Object> idPwdChk(String userid) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.idPwdChk(userid);
	}

	// id 중복확인 체크
	@Override
	public int idCheck(String id) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.idCheck(id);
	}

	// 회원가입 처리
	@Override
	public int insertMember(Map<String, Object> map) {
		return sqlSession.insert("com.spring.project.persistence.MemberDAO.insertMember", map);
	}
	
	// 패스워드 값 불러오기
	public String loadPwd(String id) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.loadPwd(id);
	}
	
	// 아이디 삭제
	@Override
	public int deleteMember(String id) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.deleteMember(id);
	}

	// 회원정보 불러오기
	@Override
	public MemberVO getMemberInfo(String id) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.getMemberInfo(id);
	}

	// 회원정보 수정 처리
	@Override
	public int modifyMember(MemberVO vo) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.modifyMember(vo);
	}
	
	// 아이디 찾기
	@Override
	public String getMemberID(Map<String, Object> map) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.getMemberID(map);
	}
	
	@Autowired
	private JavaMailSender mailSender; // xml에 등록한 bean autowired
	
	public void sendmail(String email, String key) {
		try{		      
			MimeMessage message = mailSender.createMimeMessage();        
            String txt = "모든날 모든순간 비밀번호 찾기 인증 메일입니다." 
            			 + "회원님의 임시비밀번호는" + key + "입니다.";
            message.setSubject("비밀번호 찾기 인증 메일입니다.");
            message.setText(txt, "UTF-8", "html");
            message.setFrom(new InternetAddress("admin@mss.com"));
            message.addRecipient(RecipientType.TO, new InternetAddress(email));
            mailSender.send(message);
		}catch(Exception e){
			e.printStackTrace();
		}   
	}

	@Override
	public int changePwd(Map<String, Object> map) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.changePwd(map);
	}

	@Override
	public int getMemberCnt() {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.getMemberCnt();
	}

	@Override
	public ArrayList<MemberVO> getMemberList(Map<String, Object> map) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.getMemberList(map);
	}
		
}
