package kr.spring.reservation.domain;

import java.sql.Date;

public class ReservationCommand {
	private int rv_num;
	private int rv_money;
	private int rv_people;
	private int acc_num;
	private String rv_startdate;
	private String rv_enddate;
	private String host_email;
	private String member_email;
	private int ro_num;
	private String ro_sub;
	private int ro_price;
	private Date rv_reg_date;
	private int rv_status;
	private String rv_message;
	private String rv_request;
	//--------------------------------
	private String acc_host;
	private String acc_name;
	private int ag_grade;
	private byte[] im_cover;
	private String im_cover_name;
	private String acc_address1;
	private String acc_address2;
	private String acc_in;
	private String acc_out;
	private String se_name;
	
	public String getAcc_host() {
		return acc_host;
	}
	public void setAcc_host(String acc_host) {
		this.acc_host = acc_host;
		this.setHost_email(acc_host);
	}
	public int getRv_num() {
		return rv_num;
	}
	public void setRv_num(int rv_num) {
		this.rv_num = rv_num;
	}
	public int getRv_money() {
		return rv_money;
	}
	public void setRv_money(int rv_money) {
		this.rv_money = rv_money;
	}
	public int getRv_people() {
		return rv_people;
	}
	public void setRv_people(int rv_people) {
		this.rv_people = rv_people;
	}
	public int getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(int acc_num) {
		this.acc_num = acc_num;
	}
	public String getRv_startdate() {
		return rv_startdate;
	}
	public void setRv_startdate(String rv_startdate) {
		this.rv_startdate = rv_startdate;
	}
	public String getRv_enddate() {
		return rv_enddate;
	}
	public void setRv_enddate(String rv_enddate) {
		this.rv_enddate = rv_enddate;
	}
	public String getHost_email() {
		return host_email;
	}
	public void setHost_email(String host_email) {
		this.host_email = host_email;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getRo_num() {
		return ro_num;
	}
	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}
	public String getRo_sub() {
		return ro_sub;
	}
	public void setRo_sub(String ro_sub) {
		this.ro_sub = ro_sub;
	}
	public int getRo_price() {
		return ro_price;
	}
	public void setRo_price(int ro_price) {
		this.ro_price = ro_price;
	}
	public Date getRv_reg_date() {
		return rv_reg_date;
	}
	public void setRv_reg_date(Date rv_reg_date) {
		this.rv_reg_date = rv_reg_date;
	}
	public int getRv_status() {
		return rv_status;
	}
	public void setRv_status(int rv_status) {
		this.rv_status = rv_status;
	}
	public String getRv_message() {
		return rv_message;
	}
	public void setRv_message(String rv_message) {
		this.rv_message = rv_message;
	}
	public String getRv_request() {
		return rv_request;
	}
	public void setRv_request(String rv_request) {
		this.rv_request = rv_request;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public int getAg_grade() {
		return ag_grade;
	}
	public void setAg_grade(int ag_grade) {
		this.ag_grade = ag_grade;
	}
	public byte[] getIm_cover() {
		return im_cover;
	}
	public void setIm_cover(byte[] im_cover) {
		this.im_cover = im_cover;
	}
	public String getIm_cover_name() {
		return im_cover_name;
	}
	public void setIm_cover_name(String im_cover_name) {
		this.im_cover_name = im_cover_name;
	}
	public String getAcc_address1() {
		return acc_address1;
	}
	public void setAcc_address1(String acc_address1) {
		this.acc_address1 = acc_address1;
	}
	public String getAcc_address2() {
		return acc_address2;
	}
	public void setAcc_address2(String acc_address2) {
		this.acc_address2 = acc_address2;
	}
	public String getAcc_in() {
		return acc_in;
	}
	public void setAcc_in(String acc_in) {
		this.acc_in = acc_in;
	}
	public String getAcc_out() {
		return acc_out;
	}
	public void setAcc_out(String acc_out) {
		this.acc_out = acc_out;
	}
	public String getSe_name() {
		return se_name;
	}
	public void setSe_name(String se_name) {
		this.se_name = se_name;
	}
	@Override
	public String toString() {
		return "ReservationCommand [rv_num=" + rv_num + ", rv_money=" + rv_money + ", rv_people=" + rv_people
				+ ", acc_num=" + acc_num + ", rv_startdate=" + rv_startdate + ", rv_enddate=" + rv_enddate
				+ ", host_email=" + host_email + ", member_email=" + member_email + ", ro_num=" + ro_num + ", ro_sub="
				+ ro_sub + ", ro_price=" + ro_price + ", rv_reg_date=" + rv_reg_date + ", rv_status=" + rv_status
				+ ", rv_message=" + rv_message + ", rv_request=" + rv_request + ", acc_name=" + acc_name + ", ag_grade="
				+ ag_grade + ", im_cover_name=" + im_cover_name + ", acc_address1=" + acc_address1 + ", acc_address2="
				+ acc_address2 + ", acc_in=" + acc_in + ", acc_out=" + acc_out + ", se_name=" + se_name + "]";
	}

	
	
}