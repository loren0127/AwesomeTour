package kr.spring.reservation.domain;

public class PaymentCommand {
	int pm_num;
	String pm_name;
	String pm_email;
	char pm_type;
	// 카드일시
	String pm_cardNum;
	String pm_cvc;
	String pm_validity;
	//계좌 일시
	int pm_account;
	String pm_depositor;
	String pm_deposit_ac;
	
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

	public char getPm_type() {
		return pm_type;
	}

	public void setPm_type(char pm_type) {
		this.pm_type = pm_type;
	}

	public String getPm_cardNum() {
		return pm_cardNum;
	}

	public void setPm_cardNum(String pm_cardNum) {
		this.pm_cardNum = pm_cardNum;
	}

	public String getPm_cvc() {
		return pm_cvc;
	}

	public void setPm_cvc(String pm_cvc) {
		this.pm_cvc = pm_cvc;
	}

	public String getPm_validity() {
		return pm_validity;
	}

	public void setPm_validity(String pm_validity) {
		this.pm_validity = pm_validity;
	}

	public int getPm_account() {
		return pm_account;
	}

	public void setPm_account(int pm_account) {
		this.pm_account = pm_account;
	}

	public String getPm_depositor() {
		return pm_depositor;
	}

	public void setPm_depositor(String pm_depositor) {
		this.pm_depositor = pm_depositor;
	}

	public String getPm_deposit_ac() {
		return pm_deposit_ac;
	}

	public void setPm_deposit_ac(String pm_deposit_ac) {
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
				+ ", pm_account=" + pm_account + ", pm_depositor=" + pm_depositor + ", pm_deposit_ac=" + pm_deposit_ac
				+ ", rv_message=" + rv_message + "]";
	}
	
	
}