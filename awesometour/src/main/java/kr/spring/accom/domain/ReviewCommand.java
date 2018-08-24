package kr.spring.accom.domain;

import java.sql.Date;

public class ReviewCommand {
	private int re_num;
	private int re_acc_num;
	private String re_id;
	private Date re_reg_date;
	private String re_content;
	private int re_like;
	private int re_grade;
	
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
	public String getRe_id() {
		return re_id;
	}
	public void setRe_id(String re_id) {
		this.re_id = re_id;
	}
	public Date getRe_reg_date() {
		return re_reg_date;
	}
	public void setRe_reg_date(Date re_reg_date) {
		this.re_reg_date = re_reg_date;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public int getRe_like() {
		return re_like;
	}
	public void setRe_like(int re_like) {
		this.re_like = re_like;
	}
	public int getRe_grade() {
		return re_grade;
	}
	public void setRe_grade(int re_grade) {
		this.re_grade = re_grade;
	}
	
	@Override
	public String toString() {
		return "ReviewCommand [re_num=" + re_num + ", re_acc_num=" + re_acc_num + ", re_id=" + re_id + ", re_reg_date="
				+ re_reg_date + ", re_content=" + re_content + ", re_like=" + re_like + ", re_grade=" + re_grade + "]";
	}
}
