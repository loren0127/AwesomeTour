package kr.spring.accomList.domain;

import java.util.Arrays;

public class AccomListCommand {
	private int acc_num; //숙소번호
	private String acc_name;//숙소 이름
	private int acc_grade;//호텔성급
	private String acc_theme;//숙소테마
	private String acc_address1;//숙소 주소
	private int ro_num; //방번호
	private int ro_price;//1박요금
	private int ro_pe_count;//인원수
	private String ro_sub; //호텔/프라이빗 구분
	private String rv_start_date;//예약 시작날짜
	private String rv_end_date;//예약끝날짜
	private int se_num;
	private byte[] im_cover;
	private String im_cover_name;
	private String se_name;
	
	
	
	public String getSe_name() {
		return se_name;
	}
	public void setSe_name(String se_name) {
		this.se_name = se_name;
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
	
	public int getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(int acc_num) {
		this.acc_num = acc_num;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public int getAcc_grade() {
		return acc_grade;
	}
	public void setAcc_grade(int acc_grade) {
		this.acc_grade = acc_grade;
	}
	public String getAcc_theme() {
		return acc_theme;
	}
	public void setAcc_theme(String acc_theme) {
		this.acc_theme = acc_theme;
	}
	public int getRo_num() {
		return ro_num;
	}
	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}
	public int getRo_price() {
		return ro_price;
	}
	public void setRo_price(int ro_price) {
		this.ro_price = ro_price;
	}
	public int getRo_pe_count() {
		return ro_pe_count;
	}
	public void setRo_pe_count(int ro_pe_count) {
		this.ro_pe_count = ro_pe_count;
	}
	public String getRo_sub() {
		return ro_sub;
	}
	public void setRo_sub(String ro_sub) {
		this.ro_sub = ro_sub;
	}
	public String getRv_start_date() {
		return rv_start_date;
	}
	public void setRv_start_date(String rv_start_date) {
		this.rv_start_date = rv_start_date;
	}
	public String getRv_end_date() {
		return rv_end_date;
	}
	public void setRv_end_date(String rv_end_date) {
		this.rv_end_date = rv_end_date;
	}
	public int getSe_num() {
		return se_num;
	}
	public void setSe_num(int se_num) {
		this.se_num = se_num;
	}
	@Override
	public String toString() {
		return "AccomListCommand [acc_num=" + acc_num + ", acc_name=" + acc_name + ", acc_grade=" + acc_grade
				+ ", acc_theme=" + acc_theme + ", acc_address1=" + acc_address1 + ", ro_num=" + ro_num + ", ro_price="
				+ ro_price + ", ro_pe_count=" + ro_pe_count + ", ro_sub=" + ro_sub + ", rv_start_date=" + rv_start_date
				+ ", rv_end_date=" + rv_end_date + ", se_num=" + se_num + ", im_cover_name=" + im_cover_name
				+ ", se_name=" + se_name + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
