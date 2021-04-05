package com.spring.project.persistence;

import java.util.ArrayList;
import java.util.Map;

import com.spring.project.vo.MemberVO;

public interface MemberDAO {
	
	// 로그인 처리 페이지
	public Map<String, Object> idPwdChk(String userid);
	
	// 아이디 중복확인 체크
	public int idCheck(String id);
	
	// 회원가입 처리
	public int insertMember(Map<String, Object> map);
	
	// 패스워드값 불러오기
	public String loadPwd(String id);
	
	// 회원 탈퇴
	public int deleteMember(String id);
	
	// 회원 정보 불러오기
	public MemberVO getMemberInfo(String id);
	
	// 정보 수정 처리
	public int modifyMember(MemberVO vo);
	
	// 이름, 주민번호로 아이디 찾기
	public String getMemberID(Map<String, Object> map);
	
	// 이메일 인증
	public void sendmail(String email, String key);
	
	// 비밀번호 랜덤으로 바꾸기
	public int changePwd(Map<String, Object> map);
	
	// 회원 갯수 구하기
	public int getMemberCnt();
	
	// 리스트 불러오기
	public ArrayList<MemberVO> getMemberList(Map<String, Object> map);
}
