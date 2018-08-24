package kr.spring.accom.domain;

public class ServiceCommand {
	private String se_name;
	private int se_num;
	private int se_acc_num;
	private int se_ho_num;
	
	public String getSe_name() {
		return se_name;
	}
	public void setSe_name(String se_name) {
		this.se_name = se_name;
	}
	public int getSe_num() {
		return se_num;
	}
	public void setSe_num(int se_num) {
		this.se_num = se_num;
	}
	public int getSe_acc_num() {
		return se_acc_num;
	}
	public void setSe_acc_num(int se_acc_num) {
		this.se_acc_num = se_acc_num;
	}
	public int getSe_ho_num() {
		return se_ho_num;
	}
	public void setSe_ho_num(int se_ho_num) {
		this.se_ho_num = se_ho_num;
	}
	
	@Override
	public String toString() {
		return "ServiceCommand [se_name=" + se_name + ", se_num=" + se_num + ", se_acc_num=" + se_acc_num
				+ ", se_ho_num=" + se_ho_num + "]";
	}
}
