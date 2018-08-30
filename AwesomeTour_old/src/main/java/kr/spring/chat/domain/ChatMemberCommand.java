package kr.spring.chat.domain;

import java.sql.Date;

public class ChatMemberCommand {
	private int chat_member_num; //Primary key, Not null
	private int chat_all_num; //Foreign key, Not null
	private String member_email; //Foreign key, Not null
	private Date chat_member_mod_date; //Default SYSDATE, Not null
	
	public int getChat_member_num() {
		return chat_member_num;
	}
	public void setChat_member_num(int chat_member_num) {
		this.chat_member_num = chat_member_num;
	}
	public int getChat_all_num() {
		return chat_all_num;
	}
	public void setChat_all_num(int chat_all_num) {
		this.chat_all_num = chat_all_num;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public Date getChat_member_mod_date() {
		return chat_member_mod_date;
	}
	public void setChat_member_mod_date(Date chat_member_mod_date) {
		this.chat_member_mod_date = chat_member_mod_date;
	}
	
	@Override
	public String toString() {
		return "ChatMemberCommand [chat_member_num=" + chat_member_num + ", chat_all_num=" + chat_all_num
				+ ", member_email=" + member_email + ", chat_member_mod_date=" + chat_member_mod_date + "]";
	}
}
