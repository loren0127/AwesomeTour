package kr.spring.reservation.domain;

public class PaymentCommand {
	int pm_num;
	String pm_name;
	String pm_email;
	int pm_type;
	// 카드일시
	int pm_cardNum;
	int pm_cvc;
	int pm_validity;
	//계좌 일시
	int pm_account;
	String pm_ac_name;
	int pm_deposit_ac;
	
	String rv_message;

	
	public int getPm_num() {
		return pm_num;
	}
	public void setPm_num(int pm_num) {
		this.pm_num = pm_num;
	}
	public String getPm_name() {
		return pm_name;
	}
	public void setPm_name(String pm_name) {
		this.pm_name = pm_name;
	}
	public String getPm_email() {
		return pm_email;
	}
	public void setPm_email(String pm_email) {
		this.pm_email = pm_email;
	}
	public int getPm_type() {
		return pm_type;
	}
	public void setPm_type(int pm_type) {
		this.pm_type = pm_type;
	}
	public int getPm_cardNum() {
		return pm_cardNum;
	}
	public void setPm_cardNum(int pm_cardNum) {
		this.pm_cardNum = pm_cardNum;
	}
	public int getPm_cvc() {
		return pm_cvc;
	}
	public void setPm_cvc(int pm_cvc) {
		this.pm_cvc = pm_cvc;
	}
	public int getPm_validity() {
		return pm_validity;
	}
	public void setPm_validity(int pm_validity) {
		this.pm_validity = pm_validity;
	}
	public int getPm_account() {
		return pm_account;
	}
	public void setPm_account(int pm_account) {
		this.pm_account = pm_account;
	}
	public String getPm_ac_name() {
		return pm_ac_name;
	}
	public void setPm_ac_name(String pm_ac_name) {
		this.pm_ac_name = pm_ac_name;
	}
	public int getPm_deposit_ac() {
		return pm_deposit_ac;
	}
	public void setPm_deposit_ac(int pm_deposit_ac) {
		this.pm_deposit_ac = pm_deposit_ac;
	}
	
	public String getRv_message() {
		return rv_message;
	}
	public void setRv_message(String rv_message) {
		this.rv_message = rv_message;
	}
	@Override
	public String toString() {
		return "PaymentCommand [pm_num=" + pm_num + ", pm_name=" + pm_name + ", pm_email=" + pm_email + ", pm_type="
				+ pm_type + ", pm_cardNum=" + pm_cardNum + ", pm_cvc=" + pm_cvc + ", pm_validity=" + pm_validity
				+ ", pm_account=" + pm_account + ", pm_ac_name=" + pm_ac_name + ", pm_deposit_ac=" + pm_deposit_ac
				+ ", rv_message=" + rv_message + "]";
	}
	

	
}
