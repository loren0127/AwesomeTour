package kr.spring.accom.domain;

import kr.spring.util.DurationFromNow;

public class ReviewCommand {
	private int re_num;
	private int re_acc_num;
	private String re_email;
	private String re_reg_date;
	private String re_content;
	private String re_ip;
	
	private int ag_num;
	private String ag_email;
	private String ag_grade;
	private int ag_acc_num;
	
	private int al_num;
	private String al_email;
	private int al_re_num;
	private int al_like;
	private int al_acc_num;
	private int al_count;
	
	private String member_nickname;
	
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public int getRe_num() {
		return re_num;
	}
	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}
	public int getRe_acc_num() {
		return re_acc_num;
	}
	public void setRe_acc_num(int re_acc_num) {
		this.re_acc_num = re_acc_num;
	}
	public String getRe_email() {
		return re_email;
	}
	public void setRe_email(String re_email) {
		this.re_email = re_email;
	}
	public String getRe_reg_date() {
		return re_reg_date;
	}
	public void setRe_reg_date(String re_reg_date) {
		this.re_reg_date = re_reg_date;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getRe_ip() {
		return re_ip;
	}
	public void setRe_ip(String re_ip) {
		this.re_ip = re_ip;
	}
	public int getAg_num() {
		return ag_num;
	}
	public void setAg_num(int ag_num) {
		this.ag_num = ag_num;
	}
	public String getAg_email() {
		return ag_email;
	}
	public void setAg_email(String ag_email) {
		this.ag_email = ag_email;
	}
	public String getAg_grade() {
		return ag_grade;
	}
	public void setAg_grade(String ag_grade) {
		this.ag_grade = ag_grade;
	}
	public int getAg_acc_num() {
		return ag_acc_num;
	}
	public void setAg_acc_num(int ag_acc_num) {
		this.ag_acc_num = ag_acc_num;
	}
	public int getAl_num() {
		return al_num;
	}
	public void setAl_num(int al_num) {
		this.al_num = al_num;
	}
	public String getAl_email() {
		return al_email;
	}
	public void setAl_email(String al_email) {
		this.al_email = al_email;
	}
	public int getAl_re_num() {
		return al_re_num;
	}
	public void setAl_re_num(int al_re_num) {
		this.al_re_num = al_re_num;
	}
	public int getAl_like() {
		return al_like;
	}
	public void setAl_like(int al_like) {
		this.al_like = al_like;
	}
	public int getAl_acc_num() {
		return al_acc_num;
	}
	public void setAl_acc_num(int al_acc_num) {
		this.al_acc_num = al_acc_num;
	}
	public int getAl_count() {
		return al_count;
	}
	public void setAl_count(int al_count) {
		this.al_count = al_count;
	}
	
	
}
