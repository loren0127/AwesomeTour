package kr.spring.accom.domain;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class ImageCommand {
	private int im_num;
	private int im_ac_num;
	private MultipartFile uploadCover;
	private byte[] im_cover;
	private String im_cover_name;
	private MultipartFile uploadImage2;
	private byte[] im_image2;
	private String im_image2_name;
	private MultipartFile uploadImage3;
	private byte[] im_image3;
	private String im_image3_name;
	
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
	public MultipartFile getUploadCover() {
		return uploadCover;
	}
	public void setUploadCover(MultipartFile uploadCover) {
		this.uploadCover = uploadCover;
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
	public MultipartFile getUploadImage2() {
		return uploadImage2;
	}
	public void setUploadImage2(MultipartFile uploadImage2) {
		this.uploadImage2 = uploadImage2;
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
	public MultipartFile getUploadImage3() {
		return uploadImage3;
	}
	public void setUploadImage3(MultipartFile uploadImage3) {
		this.uploadImage3 = uploadImage3;
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
	@Override
	public String toString() {
		return "ImageCommand [im_num=" + im_num + ", im_ac_num=" + im_ac_num + ", uploadCover=" + uploadCover
				+ ", im_cover=" + Arrays.toString(im_cover) + ", im_cover_name=" + im_cover_name + ", uploadImage2="
				+ uploadImage2 + ", im_image2=" + Arrays.toString(im_image2) + ", im_image2_name=" + im_image2_name
				+ ", uploadImage3=" + uploadImage3 + ", im_image3=" + Arrays.toString(im_image3) + ", im_image3_name="
				+ im_image3_name + "]";
	}
}
