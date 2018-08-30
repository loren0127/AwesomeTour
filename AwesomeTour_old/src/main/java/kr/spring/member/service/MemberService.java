package kr.spring.member.service;

import kr.spring.member.domain.CodeCommand;
import kr.spring.member.domain.MemberCommand;

public interface MemberService {
	public void insert(MemberCommand member);
	public MemberCommand selectMember(String member_memail);
	public void update(MemberCommand member);
	public void updateAuth(String member_email);
	public void delete(String id);
	public void insertCode(CodeCommand codeCommand);
	public void insertCode2(CodeCommand code);
	public int selectCode(CodeCommand codeCommand);
	public int selectCode2(CodeCommand codeCommand);
	public void deleteCode(String code);
	public void deleteCode2(CodeCommand codeCommand);
	public int checkEmail(String email);
	public int selectEmail(String email);
	public MemberCommand selectMemb(String member_email);
	public int selectPasswd(MemberCommand memberCommand);
	public void deleteDetail(String member_email);
}
