package kr.spring.admin.domain;

import java.sql.Date;

public class AccountCommand {
	private int at_num;
	private String at_name;
	private String at_pin;
	private int at_money;
	private String at_depositor;
	private Date at_reg_date;
	
	
	public int getAt_num() {
		return at_num;
	}
	public void setAt_num(int at_num) {
		this.at_num = at_num;
	}
	public String getAt_name() {
		return at_name;
	}
	public void setAt_name(String at_name) {
		this.at_name = at_name;
	}
	public String getAt_pin() {
		return at_pin;
	}
	public void setAt_pin(String at_pin) {
		this.at_pin = at_pin;
	}
	public int getAt_money() {
		return at_money;
	}
	public void setAt_money(int at_money) {
		this.at_money = at_money;
	}
	public String getAt_depositor() {
		return at_depositor;
	}
	public void setAt_depositor(String at_depositor) {
		this.at_depositor = at_depositor;
	}
	public Date getAt_reg_date() {
		return at_reg_date;
	}
	public void setAt_reg_date(Date at_reg_date) {
		this.at_reg_date = at_reg_date;
	}
	
	
	
}
