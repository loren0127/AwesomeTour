package kr.spring.chat.domain;
 import java.sql.Date;
 public class MessageCommand {
	private int message_num; //PK
	private String message_receiver; //FK, Receive member
	private String message_sender; //FK, Send member
	private String message_title; //Message title(100byte, Korean language 50EA)
	private String message_content; //Message(1000byte, Korean language 500EA)
	
	private Date message_reg_date_type; //Date type
	
	private String message_reg_date; //String type
	
	private int message_receive_status; //Receiver message delete status
	private int message_sende_status; //Sender message delete status
	
	private String message_URL; //Send URL(None URL link: "0")
	
	private String message_type; //Send message type(normal, chatInvite, groupInvite)
	
	public int getMessage_num() {
		return message_num;
	}
 	public void setMessage_num(int message_num) {
		this.message_num = message_num;
	}
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
 	public String getMessage_content() {
		return message_content;
	}
 	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
 	public Date getMessage_reg_date_type() {
		return message_reg_date_type;
	}
 	public void setMessage_reg_date_type(Date message_reg_date_type) {
		this.message_reg_date_type = message_reg_date_type;
	}
 	public String getMessage_reg_date() {
		return message_reg_date;
	}
 	public void setMessage_reg_date(String message_reg_date) {
		this.message_reg_date = message_reg_date;
	}
 	
 	public int getMessage_receive_status() {
		return message_receive_status;
	}
	public void setMessage_receive_status(int message_receive_status) {
		this.message_receive_status = message_receive_status;
	}
	public int getMessage_sende_status() {
		return message_sende_status;
	}
	public void setMessage_sende_status(int message_sende_status) {
		this.message_sende_status = message_sende_status;
	}
	public String getMessage_URL() {
		return message_URL;
	}
	public void setMessage_URL(String message_URL) {
		this.message_URL = message_URL;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	
	@Override
	public String toString() {
		return "MessageCommand [message_num=" + message_num + ", message_receiver=" + message_receiver
				+ ", message_sender=" + message_sender + ", message_title=" + message_title + ", message_content="
				+ message_content + ", message_reg_date_type=" + message_reg_date_type + ", message_reg_date="
				+ message_reg_date + ", message_receive_status=" + message_receive_status + ", message_sende_status="
				+ message_sende_status + ", message_URL=" + message_URL + ", message_type=" + message_type + "]";
	}
}