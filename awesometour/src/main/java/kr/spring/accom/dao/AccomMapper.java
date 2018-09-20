package kr.spring.accom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.domain.ReviewCommand;
import kr.spring.group.domain.GroupCommand;

public interface AccomMapper {
	//헤더 지도
	public List<AccomCommand> selectSearchList(Map<String,Object> map);
	
	public List<AccomCommand> selectList(Map<String,Object> map);
	
	//숙소 이미지 불러오기
	public ImageCommand selectImage(Integer im_ac_num);
	
	//==========메인 페이지==========//
	//슈퍼호스트 숙소 불러오기
	public List<AccomCommand> selectRecommendList(Map<String,Object> map);
	
	//리뷰 불러오기
	@Select("SELECT * FROM (SELECT re.*, rownum rnum FROM (SELECT re_acc_num, re_email, re_reg_date reg_date, re_content FROM accom_review ORDER BY re_reg_date DESC)re) WHERE rnum >= 1 AND rnum <= 4")
	public List<ReviewCommand> selectReviewList();
	
	//인기 모임 불러오기
	@Select("SELECT * FROM (SELECT gr.*, rownum rnum FROM (SELECT g_num, g_name, g_address2, g_hobby, g_reg_date, g_isprivate, g_issearch FROM group_table WHERE g_isprivate = 0 AND g_issearch = 0 ORDER BY g_reg_date DESC)gr) WHERE rnum >=1 AND rnum <= 4")
	public List<GroupCommand> selectGroupList();
	
	//모임 이미지 불러오기 --> 그룹 뷰 사용
}
