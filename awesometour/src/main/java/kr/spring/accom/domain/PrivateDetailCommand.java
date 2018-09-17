package kr.spring.accom.domain;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class PrivateDetailCommand {
	 //이미지 테이블
	private int im_num;//식별번호
	private int im_ac_num;//호텔번호
	private int im_ro_num;//룸번호
	private MultipartFile uploadCover;
	private byte[] im_cover;
	private String im_cover_name;
	private MultipartFile uploadImage2;
	private byte[] im_image2;
	private String im_image2_name;
	private MultipartFile uploadImage3;
	private byte[] im_image3;
	private String im_image3_name;
	//호텔 테이블
	private int acc_num;
	private String acc_name;
	private String acc_address1;
	private String acc_in;
	private String acc_out;
	private String acc_theme;
	private String acc_host;
	//서비스 테이블
	private String se_name;
	//멤버테이블(호스트 소개)
	//예약테이블
	private int rv_acc_num;
	private String rv_start_date;
	private String rv_end_date;
	//날짜
	private String start_date;
	private String end_date;
	//별점 테이블
	private double ag_grade;
	//룸테이블
	private int ro_acc_num;
	private int ro_price;
	//회원테이블
	private String member_content;
	private String member_email;
	private String member_nickname;
	private Date member_reg_date;
	
	
	public String getAcc_host() {
		return acc_host;
	}
	public void setAcc_host(String acc_host) {
		this.acc_host = acc_host;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public Date getMember_reg_date() {
		return member_reg_date;
	}
	public void setMember_reg_date(Date member_reg_date) {
		this.member_reg_date = member_reg_date;
	}
	public String getMember_content() {
		return member_content;
	}
	public void setMember_content(String member_content) {
		this.member_content = member_content;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getRv_acc_num() {
		return rv_acc_num;
	}
	public void setRv_acc_num(int rv_acc_num) {
		this.rv_acc_num = rv_acc_num;
	}
	public int getRo_acc_num() {
		return ro_acc_num;
	}
	public void setRo_acc_num(int ro_acc_num) {
		this.ro_acc_num = ro_acc_num;
	}
	public int getRo_price() {
		return ro_price;
	}
	public void setRo_price(int ro_price) {
		this.ro_price = ro_price;
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
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getIm_num() {
		return im_num;
	}
	public void setIm_num(int im_num) {
		this.im_num = im_num;
	}
	public int getIm_ac_num() {
		return im_ac_num;
	}
	public void setIm_ac_num(int im_ac_num) {
		this.im_ac_num = im_ac_num;
	}
	public int getIm_ro_num() {
		return im_ro_num;
	}
	public void setIm_ro_num(int im_ro_num) {
		this.im_ro_num = im_ro_num;
	}
	public byte[] getIm_cover() {
		return im_cover;
	}
	public void setIm_cover(byte[] im_cover) {
		this.im_cover = im_cover;
	}
	public String getIm_cover_name() {
		return im_cover_name;
	}
	public void setIm_cover_name(String im_cover_name) {
		this.im_cover_name = im_cover_name;
	}
	public byte[] getIm_image2() {
		return im_image2;
	}
	public void setIm_image2(byte[] im_image2) {
		this.im_image2 = im_image2;
	}
	public String getIm_image2_name() {
		return im_image2_name;
	}
	public void setIm_image2_name(String im_image2_name) {
		this.im_image2_name = im_image2_name;
	}
	public byte[] getIm_image3() {
		return im_image3;
	}
	public void setIm_image3(byte[] im_image3) {
		this.im_image3 = im_image3;
	}
	public String getIm_image3_name() {
		return im_image3_name;
	}
	public void setIm_image3_name(String im_image3_name) {
		this.im_image3_name = im_image3_name;
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
	public String getAcc_address1() {
		return acc_address1;
	}
	public void setAcc_address1(String acc_address1) {
		this.acc_address1 = acc_address1;
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
	public String getSe_name() {
		return se_name;
	}
	public void setSe_name(String se_name) {
		this.se_name = se_name;
	}
	public double getAg_grade() {
		return ag_grade;
	}
	public void setAg_grade(double ag_grade) {
		this.ag_grade = ag_grade;
	}
	public MultipartFile getUploadCover() {
		return uploadCover;
	}
	public MultipartFile getUploadImage2() {
		return uploadImage2;
	}
	public MultipartFile getUploadImage3() {
		return uploadImage3;
	}
	public void setUploadCover(MultipartFile uploadCover) throws IOException{
		this.uploadCover = uploadCover;
		
		setIm_cover(uploadCover.getBytes());
		//파일이름 가져오기
		setIm_cover_name(uploadCover.getOriginalFilename());
	}
	public void setUploadImage2(MultipartFile uploadImage2) throws IOException{
		this.uploadImage2 = uploadImage2;
		
		setIm_image2(uploadImage2.getBytes());
		//파일이름 가져오기
		setIm_image2_name(uploadImage2.getOriginalFilename());
	}
	public void setUploadImage3(MultipartFile uploadImage3) throws IOException{
		this.uploadImage3 = uploadImage3;
		
		setIm_image3(uploadImage3.getBytes());
		//파일이름 가져오기
		setIm_image3_name(uploadImage3.getOriginalFilename());
	}
}
