package kr.spring.accomList.domain;


public class AccomListCommand {
	private int acc_num; //���ҹ�ȣ
	private String acc_name;//���� �̸�
	private int acc_grade;//ȣ�ڼ���
	private String acc_theme;//�����׸�
	private String acc_address1;//���� �ּ�
	private int ro_num; //���ȣ
	private int ro_price;//1�ڿ��
	private int ro_pe_count;//�ο���
	private String ro_sub; //ȣ��/�����̺� ����
	private String rv_start_date;//���� ���۳�¥
	private String rv_end_date;//���ೡ��¥
	
	
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
	@Override
	public String toString() {
		return "AccomListCommand [acc_num=" + acc_num + ", acc_name=" + acc_name + ", acc_grade=" + acc_grade
				+ ", acc_theme=" + acc_theme + ", acc_address1=" + acc_address1 + ", ro_num=" + ro_num + ", ro_price="
				+ ro_price + ", ro_pe_count=" + ro_pe_count + ", ro_sub=" + ro_sub + ", rv_start_date=" + rv_start_date
				+ ", rv_end_date=" + rv_end_date + "]";
	}
	
	
	
	
	
	
	
	
}
