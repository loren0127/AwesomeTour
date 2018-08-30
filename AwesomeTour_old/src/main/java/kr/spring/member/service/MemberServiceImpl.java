package kr.spring.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.domain.CodeCommand;
import kr.spring.member.domain.MemberCommand;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Resource
	private MemberMapper memberMapper;
	
	@Override
	public void insert(MemberCommand member) {
		memberMapper.insert(member);
		memberMapper.insertDetail(member);
	}

	@Override
	public MemberCommand selectMember(String member_email) {
		return memberMapper.selectMember(member_email);
	}

	@Override
	public void update(MemberCommand member) {
		memberMapper.update(member);
	}


	@Override
	public void insertCode(CodeCommand codeCommand) {
		memberMapper.insertCode(codeCommand);
	}

	@Override
	public int selectCode(CodeCommand codeCommand) {
		
		return memberMapper.selectCode(codeCommand);
	}
	
	@Override
	public int selectCode2(CodeCommand codeCommand) {
		// TODO Auto-generated method stub
		return memberMapper.selectCode2(codeCommand);
	}

	@Override
	public void deleteCode(String code) {
		memberMapper.deleteCode(code);
	}

	@Override
	public int checkEmail(String email) {
		return memberMapper.checkEmail(email);
	}

	@Override
	public int selectEmail(String email) {
		// TODO Auto-generated method stub
		return memberMapper.selectEmail(email);
	}


	@Override
	public MemberCommand selectMemb(String member_email) {
		// TODO Auto-generated method stub
		return memberMapper.selectMemb(member_email);
	}

	@Override
	public int selectPasswd(MemberCommand memberCommand) {
		return memberMapper.selectPasswd(memberCommand);
	}

	@Override
	public void deleteCode2(CodeCommand codeCommand) {
		memberMapper.deleteCode2(codeCommand);
	}

	@Override
	public void insertCode2(CodeCommand codeCommand) {
		memberMapper.insertCode2(codeCommand);
		
	}

	@Override
	public void updateAuth(String member_email) {
		memberMapper.updateAuth(member_email);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDetail(String member_email) {
		memberMapper.deleteDetail(member_email);
	}

	



	

	
	

}
