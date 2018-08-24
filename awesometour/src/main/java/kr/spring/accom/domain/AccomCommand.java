package kr.spring.accom.domain;

public class AccomCommand {
	private int acc_num;
	private int acc_se_num;
	private String acc_host;
	private String acc_name;
	private int acc_grade;
	private String acc_in;
	private String acc_out;
	private String acc_theme;
	private String acc_phone;
	private String acc_zipcode;
	private String acc_address;
	private String acc_address2;
	private String acc_lati;
	private String acc_longi;
	
	public int getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(int acc_num) {
		this.acc_num = acc_num;
	}
	public int getAcc_se_num() {
		return acc_se_num;
	}
	public void setAcc_se_num(int acc_se_num) {
		this.acc_se_num = acc_se_num;
	}
	public String getAcc_host() {
		return acc_host;
	}
	public void setAcc_host(String acc_host) {
		this.acc_host = acc_host;
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
	public String getAcc_theme() {
		return acc_theme;
	}
	public void setAcc_theme(String acc_theme) {
		this.acc_theme = acc_theme;
	}
	public String getAcc_phone() {
		return acc_phone;
	}
	public void setAcc_phone(String acc_phone) {
		this.acc_phone = acc_phone;
	}
	public String getAcc_zipcode() {
		return acc_zipcode;
	}
	public void setAcc_zipcode(String acc_zipcode) {
		this.acc_zipcode = acc_zipcode;
	}
	public String getAcc_address() {
		return acc_address;
	}
	public void setAcc_address(String acc_address) {
		this.acc_address = acc_address;
	}
	public String getAcc_address2() {
		return acc_address2;
	}
	public void setAcc_address2(String acc_address2) {
		this.acc_address2 = acc_address2;
	}
	public String getAcc_lati() {
		return acc_lati;
	}
	public void setAcc_lati(String acc_lati) {
		this.acc_lati = acc_lati;
	}
	public String getAcc_longi() {
		return acc_longi;
	}
	public void setAcc_longi(String acc_longi) {
		this.acc_longi = acc_longi;
	}
	
	@Override
	public String toString() {
		return "AccomCommand [acc_num=" + acc_num + ", acc_se_num=" + acc_se_num + ", acc_host=" + acc_host
				+ ", acc_name=" + acc_name + ", acc_grade=" + acc_grade + ", acc_in=" + acc_in + ", acc_out=" + acc_out
				+ ", acc_theme=" + acc_theme + ", acc_phone=" + acc_phone + ", acc_zipcode=" + acc_zipcode
				+ ", acc_address=" + acc_address + ", acc_address2=" + acc_address2 + ", acc_lati=" + acc_lati
				+ ", acc_longi=" + acc_longi + "]";
	}
}
