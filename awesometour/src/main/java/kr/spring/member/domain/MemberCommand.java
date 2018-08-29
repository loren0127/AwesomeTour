package kr.spring.member.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class MemberCommand {
	private int member_auth;
	private String member_passwd;
	private String member_email;
	private String member_nickname;
	private Date member_reg_date;
	private String member_content;
	private String member_hobby;
	private String member_tendency;
	private MultipartFile upload;
	private byte[] member_uploadfile;
	private String member_filename;
	
	
	private Logger log = Logger.getLogger(this.getClass());
	public boolean isCheckedPasswd(String userPasswd) {
		if(log.isDebugEnabled()) {
			log.debug("<<pass2>> : " + userPasswd);
			log.debug("<<mb_pass2>> : " + member_passwd);
			
		}
	if(member_auth > 0 && member_passwd.equals(userPasswd)) {
		return true;
	}
		return false;
	}

	
	
	public int getMember_auth() {
		return member_auth;
	}



	public void setMember_auth(int member_auth) {
		this.member_auth = member_auth;
	}



	public void setUpload(MultipartFile upload) throws IOException{
		this.upload = upload;
  		//byte[] ������ ����
		setMember_uploadfile(upload.getBytes());
		//���ϸ�
		setMember_filename(upload.getOriginalFilename());
	}
	
	
	public byte[] getMember_uploadfile() {
		return member_uploadfile;
	}


	public void setMember_uploadfile(byte[] member_uploadfile) {
		this.member_uploadfile = member_uploadfile;
	}


	public String getMember_filename() {
		return member_filename;
	}


	public void setMember_filename(String member_filename) {
		this.member_filename = member_filename;
	}


	




	public String getMember_passwd() {
		return member_passwd;
	}


	public void setMember_passwd(String member_passwd) {
		this.member_passwd = member_passwd;
	}


	public String getMember_email() {
		return member_email;
	}


	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}


	public String getMember_nickname() {
		return member_nickname;
	}


	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}


	public Date getMember_reg_date() {
		return member_reg_date;
	}


	public void setMember_reg_date(Date member_reg_date) {
		this.member_reg_date = member_reg_date;
	}


	


	public String getMember_content() {
		return member_content;
	}


	public void setMember_content(String member_content) {
		this.member_content = member_content;
	}


	public String getMember_hobby() {
		return member_hobby;
	}


	public void setMember_hobby(String member_hobby) {
		this.member_hobby = member_hobby;
	}


	public String getMember_tendency() {
		return member_tendency;
	}


	public void setMember_tendency(String member_tendency) {
		this.member_tendency = member_tendency;
	}


	public MultipartFile getUpload() {
		return upload;
	}



	@Override
	public String toString() {
		return "MemberCommand [member_auth=" + member_auth + ", member_passwd=" + member_passwd + ", member_email="
				+ member_email + ", member_nickname=" + member_nickname + ", member_reg_date=" + member_reg_date
				+ ", member_content=" + member_content + ", member_hobby=" + member_hobby + ", member_tendency="
				+ member_tendency + ", upload=" + upload + ", member_uploadfile=" + Arrays.toString(member_uploadfile)
				+ ", member_filename=" + member_filename + ", log=" + log + "]";
	}
}



