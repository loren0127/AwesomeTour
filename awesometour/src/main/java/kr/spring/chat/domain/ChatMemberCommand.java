package kr.spring.chat.domain;

import java.sql.Date;

public class ChatMemberCommand {
	private int chat_member_num; //Primary key, Not null
	private int chat_all_num_member; //Foreign key, Not null
	private String member_email; //Foreign key, Not null
	
	//String type date
	private String chat_member_mod_date; //Default SYSDATE, Not null
	
	//Date type date
	private Date chat_member_mod_date_type;
	
	public int getChat_member_num() {
		return chat_member_num;
	}
	public void setChat_member_num(int chat_member_num) {
		this.chat_member_num = chat_member_num;
	}
	public int getChat_all_num_member() {
		return chat_all_num_member;
	}
	public void setChat_all_num_member(int chat_all_num_member) {
		this.chat_all_num_member = chat_all_num_member;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getChat_member_mod_date() {
		return chat_member_mod_date;
	}
	public void setChat_member_mod_date(String chat_member_mod_date) {
		this.chat_member_mod_date = chat_member_mod_date;
	}
	public Date getChat_member_mod_date_type() {
		return chat_member_mod_date_type;
	}
	public void setChat_member_mod_date_type(Date chat_member_mod_date_type) {
		this.chat_member_mod_date_type = chat_member_mod_date_type;
	}
	
	@Override
	public String toString() {
		return "ChatMemberCommand [chat_member_num=" + chat_member_num + ", chat_all_num_member=" + chat_all_num_member
				+ ", member_email=" + member_email + ", chat_member_mod_date=" + chat_member_mod_date
				+ ", chat_member_mod_date_type=" + chat_member_mod_date_type + "]";
	}
}
