package kr.spring.chat.domain;

import java.sql.Date;

public class ChatAllAndMemberCommand {
	private int chat_all_num; //Primary key, Not null
	private int group_num; //Foreign key, Default -1, Not null
	private String chat_all_sort; //Default 개인, Not null
	private int chat_all_member_max; //Default 2, Not null
	private String chat_all_title; //Not null
	private String chat_all_member_list; //Not null
	private String chat_all_content; //Not null
	
	//String type date
	private String chat_all_mod_date; //Default SYSDATE, Not null
	private String chat_all_reg_date; //Default SYSDATE, Not null
	
	//Date type date
	private Date chat_all_mod_date_type;
	private Date chat_all_reg_date_type;
	
	public int getChat_all_num() {
		return chat_all_num;
	}
	public void setChat_all_num(int chat_all_num) {
		this.chat_all_num = chat_all_num;
	}
	public int getGroup_num() {
		return group_num;
	}
	public void setGroup_num(int group_num) {
		this.group_num = group_num;
	}
	public String getChat_all_sort() {
		return chat_all_sort;
	}
	public void setChat_all_sort(String chat_all_sort) {
		this.chat_all_sort = chat_all_sort;
	}
	public int getChat_all_member_max() {
		return chat_all_member_max;
	}
	public void setChat_all_member_max(int chat_all_member_max) {
		this.chat_all_member_max = chat_all_member_max;
	}
	public String getChat_all_title() {
		return chat_all_title;
	}
	public void setChat_all_title(String chat_all_title) {
		this.chat_all_title = chat_all_title;
	}
	public String getChat_all_member_list() {
		return chat_all_member_list;
	}
	public void setChat_all_member_list(String chat_all_member_list) {
		this.chat_all_member_list = chat_all_member_list;
	}
	public String getChat_all_content() {
		return chat_all_content;
	}
	public void setChat_all_content(String chat_all_content) {
		this.chat_all_content = chat_all_content;
	}
	public String getChat_all_mod_date() {
		return chat_all_mod_date;
	}
	public void setChat_all_mod_date(String chat_all_mod_date) {
		this.chat_all_mod_date = chat_all_mod_date;
	}
	public String getChat_all_reg_date() {
		return chat_all_reg_date;
	}
	public void setChat_all_reg_date(String chat_all_reg_date) {
		this.chat_all_reg_date = chat_all_reg_date;
	}
	public Date getChat_all_mod_date_type() {
		return chat_all_mod_date_type;
	}
	public void setChat_all_mod_date_type(Date chat_all_mod_date_type) {
		this.chat_all_mod_date_type = chat_all_mod_date_type;
	}
	public Date getChat_all_reg_date_type() {
		return chat_all_reg_date_type;
	}
	public void setChat_all_reg_date_type(Date chat_all_reg_date_type) {
		this.chat_all_reg_date_type = chat_all_reg_date_type;
	}
	
	private int chat_member_num; //Primary key, Not null
	private int chat_all_num_member_fk; //Foreign key, Not null
	private String member_email; //Foreign key, Not null
	private String chat_member_mod_date; //Default SYSDATE, Not null
	
	private Date chat_member_mod_date_type;

	public int getChat_member_num() {
		return chat_member_num;
	}
	public void setChat_member_num(int chat_member_num) {
		this.chat_member_num = chat_member_num;
	}
	public int getChat_all_num_member_fk() {
		return chat_all_num_member_fk;
	}
	public void setChat_all_num_member_fk(int chat_all_num_member_fk) {
		this.chat_all_num_member_fk = chat_all_num_member_fk;
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
}
