package kr.spring.accom.domain;

public class ImageCommand {
	private int im_num;
	private int im_ac_num;
	private String im_cover_name;
	private String im_image2_name;
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
	public String getIm_cover_name() {
		return im_cover_name;
	}
	public void setIm_cover_name(String im_cover_name) {
		this.im_cover_name = im_cover_name;
	}
	public String getIm_image2_name() {
		return im_image2_name;
	}
	public void setIm_image2_name(String im_image2_name) {
		this.im_image2_name = im_image2_name;
	}
	public String getIm_image3_name() {
		return im_image3_name;
	}
	public void setIm_image3_name(String im_image3_name) {
		this.im_image3_name = im_image3_name;
	}
	
	@Override
	public String toString() {
		return "ImageCommand [im_num=" + im_num + ", im_ac_num=" + im_ac_num + ", im_cover_name=" + im_cover_name
				+ ", im_image2_name=" + im_image2_name + ", im_image3_name=" + im_image3_name + "]";
	}
}
