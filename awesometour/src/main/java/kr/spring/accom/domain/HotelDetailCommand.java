package kr.spring.accom.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class HotelDetailCommand {
	//호텔테이블
	private int acc_num;
	private String acc_name;
	private int acc_grade;
	private String acc_address1;
	private String acc_in;
	private String acc_out;
	private String acc_theme;
	private String acc_host;
	//별점 테이블
	private double ag_grade;
	//서비스테이블
	private String se_name;
	//이미지 테이블
	private int im_num;
	private int im_ac_num;
	private int im_ro_num;
	private MultipartFile uploadCover;
	private byte[] im_cover;
	private String im_cover_name;
	private MultipartFile uploadImage2;
	private byte[] im_image2;
	private String im_image2_name;
	private MultipartFile uploadImage3;
	private byte[] im_image3;
	private String im_image3_name;
	//룸테이블
	private int ro_num;
	private int ro_num2;
	private int ro_room_num;
	private String ro_bed_type;
	private int ro_bed_count;
	private int ro_price;
	private int ro_room_count;
	private int ro_pe_count;
	//예약 테이블
	private int rv_acc_num;
	private int rv_ro_num;
	private String rv_start_date;
	private String rv_end_date;
	private int rv_room_num;
	private int rv_room_count;
		
	
	public String getAcc_host() {
		return acc_host;
	}
	public void setAcc_host(String acc_host) {
		this.acc_host = acc_host;
	}
	public int getRo_pe_count() {
		return ro_pe_count;
	}
	public void setRo_pe_count(int ro_pe_count) {
		this.ro_pe_count = ro_pe_count;
	}
	public int getRo_num2() {
		return ro_num2;
	}
	public void setRo_num2(int ro_num2) {
		this.ro_num2 = ro_num2;
	}
	public int getRv_room_num() {
		return rv_room_num;
	}
	public void setRv_room_num(int rv_room_num) {
		this.rv_room_num = rv_room_num;
	}
	public int getRo_num() {
		return ro_num;
	}
	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}
	public int getRo_room_count() {
		return ro_room_count;
	}
	public void setRo_room_count(int ro_room_count) {
		this.ro_room_count = ro_room_count;
	}
	public int getRv_acc_num() {
		return rv_acc_num;
	}
	public void setRv_acc_num(int rv_acc_num) {
		this.rv_acc_num = rv_acc_num;
	}
	public int getRv_ro_num() {
		return rv_ro_num;
	}
	public void setRv_ro_num(int rv_ro_num) {
		this.rv_ro_num = rv_ro_num;
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
	public int getRv_room_count() {
		return rv_room_count;
	}
	public void setRv_room_count(int rv_room_count) {
		this.rv_room_count = rv_room_count;
	}
	public int getIm_ro_num() {
		return im_ro_num;
	}
	public void setIm_ro_num(int im_ro_num) {
		this.im_ro_num = im_ro_num;
	}
	public double getAg_grade() {
		return ag_grade;
	}
	public void setAg_grade(double ag_grade) {
		this.ag_grade = ag_grade;
	}
	public int getRo_room_num() {
		return ro_room_num;
	}
	public void setRo_room_num(int ro_room_num) {
		this.ro_room_num = ro_room_num;
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
	public int getRo_price() {
		return ro_price;
	}
	public void setRo_price(int ro_price) {
		this.ro_price = ro_price;
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
