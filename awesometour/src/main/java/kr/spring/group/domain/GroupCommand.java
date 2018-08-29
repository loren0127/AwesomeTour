package kr.spring.group.domain;

import java.sql.Date;

public class GroupCommand {
	private int g_num;
	private String g_name;
	private String g_explain;
	private String g_loc;
	private Date g_reg_date;
	private Date g_close_date;
	private int g_isPrivate;
	private String g_host;
	private String g_hobby;
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
	public String getG_loc() {
		return g_loc;
	}
	public void setG_loc(String g_loc) {
		this.g_loc = g_loc;
	}
	public Date getG_reg_date() {
		return g_reg_date;
	}
	public void setG_reg_date(Date g_reg_date) {
		this.g_reg_date = g_reg_date;
	}
	public Date getG_close_date() {
		return g_close_date;
	}
	public void setG_close_date(Date g_close_date) {
		this.g_close_date = g_close_date;
	}
	public int getG_isPrivate() {
		return g_isPrivate;
	}
	public void setG_isPrivate(int g_isPrivate) {
		this.g_isPrivate = g_isPrivate;
	}
	public String getG_host() {
		return g_host;
	}
	public void setG_host(String g_host) {
		this.g_host = g_host;
	}
	
	public String getG_hobby() {
		return g_hobby;
	}
	public void setG_hobby(String g_hobby) {
		this.g_hobby = g_hobby;
	}
	@Override
	public String toString() {
		return "GroupCommand [g_num=" + g_num + ", g_name=" + g_name + ", g_explain=" + g_explain + ", g_loc=" + g_loc
				+ ", g_reg_date=" + g_reg_date + ", g_close_date=" + g_close_date + ", g_isPrivate=" + g_isPrivate
				+ ", g_host=" + g_host + ", g_hobby=" + g_hobby + "]";
	}
	
	
	
	
	
	
	

}
