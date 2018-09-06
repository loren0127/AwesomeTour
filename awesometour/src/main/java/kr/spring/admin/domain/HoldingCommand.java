package kr.spring.admin.domain;

import java.sql.Date;

public class HoldingCommand {
	private int rv_num;
	private int hd_deposit;
	private String hd_account;
	private int hd_money;
	private int at_num;
	
	private String at_pin;
	private int at_money;
	
	private int acc_num;
	private int ro_num;
	private int rv_status;
	private int rv_money;
	private Date rv_start_date;
	private Date rv_end_date;
	private int rv_people;
	private String rv_message;
	private String rv_request;
	private String host_email;
	
	public int getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(int acc_num) {
		this.acc_num = acc_num;
	}
	public int getRo_num() {
		return ro_num;
	}
	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}
	public int getRv_status() {
		return rv_status;
	}
	public void setRv_status(int rv_status) {
		this.rv_status = rv_status;
	}
	public int getRv_money() {
		return rv_money;
	}
	public void setRv_money(int rv_money) {
		this.rv_money = rv_money;
	}
	public Date getRv_start_date() {
		return rv_start_date;
	}
	public void setRv_start_date(Date rv_start_date) {
		this.rv_start_date = rv_start_date;
	}
	public Date getRv_end_date() {
		return rv_end_date;
	}
	public void setRv_end_date(Date rv_end_date) {
		this.rv_end_date = rv_end_date;
	}
	public int getRv_people() {
		return rv_people;
	}
	public void setRv_people(int rv_people) {
		this.rv_people = rv_people;
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
	public String getHost_email() {
		return host_email;
	}
	public void setHost_email(String host_email) {
		this.host_email = host_email;
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
	public int getAt_num() {
		return at_num;
	}
	public void setAt_num(int at_num) {
		this.at_num = at_num;
	}
	public int getRv_num() {
		return rv_num;
	}
	public void setRv_num(int rv_num) {
		this.rv_num = rv_num;
	}
	public int getHd_deposit() {
		return hd_deposit;
	}
	public void setHd_deposit(int hd_deposit) {
		this.hd_deposit = hd_deposit;
	}
	public String getHd_account() {
		return hd_account;
	}
	public void setHd_account(String hd_account) {
		this.hd_account = hd_account;
	}
	public int getHd_money() {
		return hd_money;
	}
	public void setHd_money(int hd_money) {
		this.hd_money = hd_money;
	}
	
	
	
}
