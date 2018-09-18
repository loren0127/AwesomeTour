package kr.spring.group.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class GroupCommand {
	private int g_num;
	private String g_name;
	private String g_explain;
	private String g_address1;
	private String g_address2;

	private Date g_reg_date;
	private String g_close_date;
	private int g_isPrivate;
	private int g_isSearch;

	private String member_email;
	private String g_hobby;
	private MultipartFile upload;
	private byte[] g_image;
	private String g_imageName;
	private String inviteMember;
	

	public String getInviteMember() {
		return inviteMember;
	}

	public void setInviteMember(String inviteMember) {
		this.inviteMember = inviteMember;
	}

	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		
		setG_image(upload.getBytes());
		setG_imageName(upload.getOriginalFilename());
	}
	
	public int getG_num() {
		return g_num;
	}
	public void setG_num(int g_num) {
		this.g_num = g_num;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_explain() {
		return g_explain;
	}
	public void setG_explain(String g_explain) {
		this.g_explain = g_explain;
	}
	public String getG_address1() {
		return g_address1;
	}
	public void setG_address1(String g_address1) {
		this.g_address1 = g_address1;
	}
	public String getG_address2() {
		return g_address2;
	}
	public void setG_address2(String g_address2) {
		this.g_address2 = g_address2;
	}
	public Date getG_reg_date() {
		return g_reg_date;
	}
	public void setG_reg_date(Date g_reg_date) {
		this.g_reg_date = g_reg_date;
	}
	
	public String getG_close_date() {
		return g_close_date;
	}

	public void setG_close_date(String g_close_date) {
		this.g_close_date = g_close_date;
	}

	public int getG_isPrivate() {
		return g_isPrivate;
	}
	public void setG_isPrivate(int g_isPrivate) {
		this.g_isPrivate = g_isPrivate;
	}
	public int getG_isSearch() {
		return g_isSearch;
	}
	public void setG_isSearch(int g_isSearch) {
		this.g_isSearch = g_isSearch;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getG_hobby() {
		return g_hobby;
	}
	public void setG_hobby(String g_hobby) {
		this.g_hobby = g_hobby;
	}
	public MultipartFile getUpload() {
		return upload;
	}

	public byte[] getG_image() {
		return g_image;
	}
	public void setG_image(byte[] g_image) {
		this.g_image = g_image;
	}
	public String getG_imageName() {
		return g_imageName;
	}
	public void setG_imageName(String g_imageName) {
		this.g_imageName = g_imageName;
	}

	@Override
	public String toString() {
		return "GroupCommand [g_num=" + g_num + ", g_name=" + g_name + ", g_explain=" + g_explain + ", g_address1="
				+ g_address1 + ", g_address2=" + g_address2 + ", g_reg_date=" + g_reg_date + ", g_close_date="
				+ g_close_date + ", g_isPrivate=" + g_isPrivate + ", g_isSearch=" + g_isSearch + ", member_email="
				+ member_email + ", g_hobby=" + g_hobby + ", upload=" + upload + ", g_image=" + Arrays.toString(g_image)
				+ ", g_imageName=" + g_imageName + ", inviteMember=" + inviteMember + "]";
	}

	
}
