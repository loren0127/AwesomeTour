package kr.spring.member.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.domain.CodeCommand;
import kr.spring.member.domain.MemberCommand;


public interface MemberMapper {
	@Insert("INSERT INTO member_auth (member_email) VALUES (#{member_email})")
	public void insert(MemberCommand member);
	@Insert("INSERT INTO member_detail (member_passwd,member_email,member_nickname,member_reg_date,member_content,member_uploadfile,member_filename,member_tendency,member_hobby) "
			+ "VALUES (#{member_passwd},#{member_email},#{member_nickname},sysdate,#{member_content},#{member_uploadfile},#{member_filename},#{member_tendency},#{member_hobby})")
	public void insertDetail(MemberCommand member);
	
	
	@Select("SELECT * FROM member_auth m LEFT OUTER JOIN member_detail d ON m.member_email=d.member_email WHERE m.member_email=#{member_email}")
	public MemberCommand selectMember(String member_email);
	/*@Update("UPDATE member_detail SET member_email=#{member_email},member_passwd=#{member_passwd},member_nickname=#{member_nickname}"
			+ ",member_content=#{member_content},member_uploadfile=#{member_uploadfile},member_filename=#{member_filename}"
			+ " WHERE member_email=#{member_email}")*/
	public void update(MemberCommand member);
	
	
	@Select("SELECT * FROM member_detail WHERE member_email=#{member_email}")
	public MemberCommand selectMemb(String member_email);
	
	@Insert("INSERT INTO member_code (code_num,code_code,code_email) "
			+ "VALUES (fmember_code_seq.nextval,#{code_code},#{code_email})")
	public void insertCode(CodeCommand code);
	
	@Insert("INSERT INTO member_code (code_num,code_code,code_email,code_status) "
			+ "VALUES (fmember_code_seq.nextval,#{code_code},#{code_email},#{code_status})")
	public void insertCode2(CodeCommand code);
	
	//SELECT * FROM fmember_code WHERE code_code='vdgpt' AND code_email='ghcks3916@naver.com';
	@Select("SELECT COUNT(*) FROM member_code WHERE code_email=#{code_email} AND code_code=#{code_code}")
	public int selectCode(CodeCommand codeCommand);
	
	//코드 스테이터스
	@Select("SELECT COUNT(*) FROM member_code WHERE code_email=#{code_email} AND code_status=#{code_status}")
	public int selectCode2(CodeCommand codeCommand);
	
	//비밀번호 찾기
	@Select("SELECT COUNT(*) FROM member_detail WHERE member_email=#{member_email} AND member_passwd=#{member_passwd}")
	public int selectPasswd(MemberCommand memberCommand);
	
	//이메일 코드찾기 
	@Select("SELECT COUNT(*) FROM member_auth WHERE member_email=#{member_email}")
	public int checkEmail(String email);
	
	//이메일인증 전 해당 이메일 존재여부 확인
	@Select("SELECT COUNT(*) FROM member_code WHERE code_email=#{code_email}")
	public int selectEmail(String email);
	
	@Delete("DELETE FROM member_code WHERE code_email=#{code_email}")
	public int deleteCode(String code);
	
	//탈퇴 전 이메일 체크
	@Delete("DELETE FROM member_code WHERE code_email=#{code_email} AND code_status=#{code_status}")
	public void deleteCode2(CodeCommand codeCommand);
	
	//탈퇴 후 auth 값 변경
	@Update("UPDATE member_auth SET member_auth=0 WHERE member_email=#{member_email}")
	public void updateAuth(String member_email);
	
	//탈퇴 후 detail 삭제
	@Delete("DELETE FROM member_detail WHERE member_email=#{member_email}")
	public void deleteDetail(String member_email);
}




