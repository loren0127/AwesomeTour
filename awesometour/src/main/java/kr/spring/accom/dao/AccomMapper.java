package kr.spring.accom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.domain.ReviewCommand;
import kr.spring.group.domain.GroupCommand;

public interface AccomMapper {
	public List<AccomCommand> selectList(Map<String,Object> map);
	
	//숙소 이미지 불러오기
	public ImageCommand selectImage(Integer im_ac_num);
	
	//==========메인 페이지==========//
	//슈퍼호스트 숙소 불러오기
	public List<AccomCommand> selectRecommendList(Map<String,Object> map);
	
	//리뷰 불러오기
	@Select("SELECT re_acc_num, re_content, re_email, re_reg_date FROM accom_review WHERE re_acc_num=#{acc_num}")
	public ReviewCommand selectReviewList(Integer acc_num);
	
	//인기 모임 불러오기
	@Select("")
	public List<GroupCommand> selectGroupList(Map<String,Object> map);
}
