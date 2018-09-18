package kr.spring.mypage.domain;

import java.sql.Date;

public class MyPageCommand {
	private int rv_num;
	private int acc_num;
	private String member_email;
	private Date rv_reg_date;
	private int rv_status;
	private int rv_money;
	private String rv_start_date;
	private String rv_end_date;
	private int rv_people;
	private String rv_message;
	private String rv_request;
	private String host_email;
	private int ro_num;
	
	private String acc_name;
	private String member_complain_content;
	private Date member_complain_reg_date;
	private String member_rv_end_date;
	private int member_complain_num;
	private String member_complain_title;
	private int member_rv_num;
	
	private String message_receiver;
	private String message_sender;
	private String message_title;
	private Date message_reg_date;
	private int message_receive_status;
	private int message_send_status;
	private String message_content;
	private String message_url;
	private String message_type;
	
	
	public String getMessage_receiver() {
		return message_receiver;
	}
	public void setMessage_receiver(String message_receiver) {
		this.message_receiver = message_receiver;
	}
	public String getMessage_sender() {
		return message_sender;
	}
	public void setMessage_sender(String message_sender) {
		this.message_sender = message_sender;
	}
	public String getMessage_title() {
		return message_title;
	}
	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}
	public Date getMessage_reg_date() {
		return message_reg_date;
	}
	public void setMessage_reg_date(Date message_reg_date) {
		this.message_reg_date = message_reg_date;
	}
	public int getMessage_receive_status() {
		return message_receive_status;
	}
	public void setMessage_receive_status(int message_receive_status) {
		this.message_receive_status = message_receive_status;
	}
	public int getMessage_send_status() {
		return message_send_status;
	}
	public void setMessage_send_status(int message_send_status) {
		this.message_send_status = message_send_status;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public String getMessage_url() {
		return message_url;
	}
	public void setMessage_url(String message_url) {
		this.message_url = message_url;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public int getMember_rv_num() {
		return member_rv_num;
	}
	public void setMember_rv_num(int member_rv_num) {
		this.member_rv_num = member_rv_num;
	}
	public String getMember_complain_title() {
		return member_complain_title;
	}
	public void setMember_complain_title(String member_complain_title) {
		this.member_complain_title = member_complain_title;
	}
	public int getMember_complain_num() {
		return member_complain_num;
	}
	public void setMember_complain_num(int member_complain_num) {
		this.member_complain_num = member_complain_num;
	}
	public Date getMember_complain_reg_date() {
		return member_complain_reg_date;
	}
	public void setMember_complain_reg_date(Date member_complain_reg_date) {
		this.member_complain_reg_date = member_complain_reg_date;
	}
	public String getMember_rv_end_date() {
		return member_rv_end_date;
	}
	public void setMember_rv_end_date(String member_rv_end_date) {
		this.member_rv_end_date = member_rv_end_date;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public String getMember_complain_content() {
		return member_complain_content;
	}
	public void setMember_complain_content(String member_complain_content) {
		this.member_complain_content = member_complain_content;
	}
	public int getRo_num() {
		return ro_num;
	}
	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}
	public int getRv_num() {
		return rv_num;
	}
	public void setRv_num(int rv_num) {
		this.rv_num = rv_num;
	}
	public int getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(int acc_num) {
		this.acc_num = acc_num;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
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
	public int getRv_money() {
		return rv_money;
	}
	public void setRv_money(int rv_money) {
		this.rv_money = rv_money;
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
	
}
