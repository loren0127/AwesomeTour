package kr.spring.chat.domain;

import java.sql.Date;

public class ChatAllTalkCommand {
	private int chat_all_talk_num;
	private int chat_all_num;
	private String member_email;
	private String chat_all_talk_content;
	private String chat_all_talk_nickname;
	
	//String type date
	private String chat_all_talk_reg_date;
	
	//Date type date
	private Date chat_all_talk_reg_date_type;

	public int getChat_talk_num() {
		return chat_all_talk_num;
	}

	public void setChat_talk_num(int chat_all_talk_num) {
		this.chat_all_talk_num = chat_all_talk_num;
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

	public String getChat_all_talk_content() {
		return chat_all_talk_content;
	}

	public void setChat_all_talk_content(String chat_all_talk_content) {
		this.chat_all_talk_content = chat_all_talk_content;
	}

	public String getChat_all_talk_reg_date() {
		return chat_all_talk_reg_date;
	}

	public void setChat_all_talk_reg_date(String chat_all_talk_reg_date) {
		this.chat_all_talk_reg_date = chat_all_talk_reg_date;
	}

	public Date getChat_all_talk_reg_date_type() {
		return chat_all_talk_reg_date_type;
	}

	public void setChat_all_talk_reg_date_type(Date chat_all_talk_reg_date_type) {
		this.chat_all_talk_reg_date_type = chat_all_talk_reg_date_type;
	}

	public int getChat_all_talk_num() {
		return chat_all_talk_num;
	}

	public void setChat_all_talk_num(int chat_all_talk_num) {
		this.chat_all_talk_num = chat_all_talk_num;
	}

	public String getChat_all_talk_nickname() {
		return chat_all_talk_nickname;
	}

	public void setChat_all_talk_nickname(String chat_all_talk_nickname) {
		this.chat_all_talk_nickname = chat_all_talk_nickname;
	}

	@Override
	public String toString() {
		return "ChatAllTalkCommand [chat_talk_num=" + chat_all_talk_num + ", chat_all_num=" + chat_all_num
				+ ", member_email=" + member_email + ", chat_all_talk_content=" + chat_all_talk_content
				+ ", chat_all_talk_reg_date=" + chat_all_talk_reg_date + ", chat_all_talk_reg_date_type="
				+ chat_all_talk_reg_date_type + "]";
	}
}
