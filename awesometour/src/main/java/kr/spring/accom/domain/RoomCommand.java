package kr.spring.accom.domain;

public class RoomCommand {
	private int ro_num;
	private int ro_acc_num;
	private int ro_se_num;
	private int ro_room_num;
	private String ro_sub;
	private int ro_price;
	private int ro_pe_count;
	private String ro_type;
	private String ro_bed_type;
	private int ro_bed_count;
	private int ro_bath_count;
	
	public int getRo_num() {
		return ro_num;
	}
	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}
	public int getRo_acc_num() {
		return ro_acc_num;
	}
	public void setRo_acc_num(int ro_acc_num) {
		this.ro_acc_num = ro_acc_num;
	}
	public int getRo_se_num() {
		return ro_se_num;
	}
	public void setRo_se_num(int ro_se_num) {
		this.ro_se_num = ro_se_num;
	}
	public int getRo_room_num() {
		return ro_room_num;
	}
	public void setRo_room_num(int ro_room_num) {
		this.ro_room_num = ro_room_num;
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
	public int getRo_pe_count() {
		return ro_pe_count;
	}
	public void setRo_pe_count(int ro_pe_count) {
		this.ro_pe_count = ro_pe_count;
	}
	public String getRo_type() {
		return ro_type;
	}
	public void setRo_type(String ro_type) {
		this.ro_type = ro_type;
	}
	public String getRo_bed_type() {
		return ro_bed_type;
	}
	public void setRo_bed_type(String ro_bed_type) {
		this.ro_bed_type = ro_bed_type;
	}
	public int getRo_bed_count() {
		return ro_bed_count;
	}
	public void setRo_bed_count(int ro_bed_count) {
		this.ro_bed_count = ro_bed_count;
	}
	public int getRo_bath_count() {
		return ro_bath_count;
	}
	public void setRo_bath_count(int ro_bath_count) {
		this.ro_bath_count = ro_bath_count;
	}
	
	@Override
	public String toString() {
		return "RoomCommand [ro_num=" + ro_num + ", ro_acc_num=" + ro_acc_num + ", ro_se_num=" + ro_se_num
				+ ", ro_room_num=" + ro_room_num + ", ro_sub=" + ro_sub + ", ro_price=" + ro_price + ", ro_pe_count="
				+ ro_pe_count + ", ro_type=" + ro_type + ", ro_bed_type=" + ro_bed_type + ", ro_bed_count="
				+ ro_bed_count + ", ro_bath_count=" + ro_bath_count + "]";
	}
}
