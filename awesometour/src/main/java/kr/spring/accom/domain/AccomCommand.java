package kr.spring.accom.domain;

public class AccomCommand {
	private int acc_num;
	private String acc_host;
	private String acc_name;
	private int acc_grade;
	private String acc_in;
	private String acc_out;
	private String acc_theme;
	private String acc_phone;
	private String acc_zipcode;
	private String acc_address1;
	private String acc_address2;
	private String acc_lati;
	private String acc_longi;
	private int ro_price;
	private String ro_sub;
	private String ag_grade;
	private String member_auth;
	private int review_count;
	
	public int getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(int acc_num) {
		this.acc_num = acc_num;
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
	public int getRo_price() {
		return ro_price;
	}
	public void setRo_price(int ro_price) {
		this.ro_price = ro_price;
	}
	public String getRo_sub() {
		return ro_sub;
	}
	public void setRo_sub(String ro_sub) {
		this.ro_sub = ro_sub;
	}
	public String getAg_grade() {
		return ag_grade;
	}
	public void setAg_grade(String ag_grade) {
		this.ag_grade = ag_grade;
	}
	public String getMember_auth() {
		return member_auth;
	}
	public void setMember_auth(String member_auth) {
		this.member_auth = member_auth;
	}
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	@Override
	public String toString() {
		return "AccomCommand [acc_num=" + acc_num + ", acc_host=" + acc_host + ", acc_name=" + acc_name + ", acc_grade="
				+ acc_grade + ", acc_in=" + acc_in + ", acc_out=" + acc_out + ", acc_theme=" + acc_theme
				+ ", acc_phone=" + acc_phone + ", acc_zipcode=" + acc_zipcode + ", acc_address1=" + acc_address1
				+ ", acc_address2=" + acc_address2 + ", acc_lati=" + acc_lati + ", acc_longi=" + acc_longi
				+ ", ro_price=" + ro_price + ", ro_sub=" + ro_sub + ", ag_grade=" + ag_grade + ", member_auth="
				+ member_auth + ", review_count=" + review_count + "]";
	}
}
