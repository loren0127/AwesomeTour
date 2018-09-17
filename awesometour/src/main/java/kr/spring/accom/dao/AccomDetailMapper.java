package kr.spring.accom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.accom.domain.HotelDetailCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.domain.PrivateDetailCommand;
import kr.spring.accom.domain.ReviewCommand;

public interface AccomDetailMapper {
	//이미지 등록(나중에 제거)
	@Insert("INSERT INTO accom_image (im_num,im_cover,im_cover_name,im_image2,im_image2_name,im_image3,im_image3_name) VALUES (image_seq.nextval,#{im_cover},#{im_cover_name},#{im_image2},#{im_image2_name},#{im_image3},#{im_image3_name})")
	public void insertImage(ImageCommand image);
	//=====호텔 상세 페이지======
	//호텔 전체 이미지 가져오기
	public List<HotelDetailCommand> selectHotelTotalImage(Integer im_ac_num);
	public HotelDetailCommand selectHotelImage(Map<String,Object> map);
	//상세페이지 상단
	public HotelDetailCommand selectHotelDetail(Integer im_ac_num);
	//서비스 테이블
	@Select("SELECT se_name FROM accom_service WHERE se_acc_num=#{im_ac_num}")
	public String selectService(Integer im_ac_num);
	//스탠다드룸
	public Integer selectStandardRoomCount(Map<String,Object> map);
	public List<HotelDetailCommand> selectStandardRoom(Map<String,Object> map);
	//디럭스룸
	public Integer selectDeluxRoomCount(Map<String,Object> map);
	public List<HotelDetailCommand> selectDeluxRoom(Map<String,Object> map);
	//스위트룸
	public Integer selectSuiteRoomCount(Map<String,Object> map);
	public List<HotelDetailCommand> selectSuiteRoom(Map<String,Object> map);
	//=====호텔 후기=====
	//리스트
	//좋아요순
	public List<ReviewCommand> selectListReview(Map<String, Object> map);
	//최신순
	public List<ReviewCommand> selectListReview2(Map<String,Object> map);
	//리스트 카운트
	public int selectReviewCount(Map<String, Object> map);
	//후기 작성시 해당 숙소를 현재 날짜보다 이전에 예약한 사람만 작성가능
	@Select("SELECT rv_start_date,rv_end_date FROM reservation WHERE SYSDATE > rv_end_date AND member_email=#{re_email} AND acc_num=#{re_acc_num} AND rv_status=2")
	public HotelDetailCommand selectRvReivew(Map<String,Object> map);
	//후기 등록
	@Insert("INSERT INTO accom_review (re_num,re_acc_num,re_email,re_reg_date,re_content,re_ip) VALUES (review_seq.nextval,#{re_acc_num},#{re_email},sysdate,#{re_content},#{re_ip})")
	public void insertReview(Map<String,Object> map);
	//후기 수정
	@Update("UPDATE accom_review SET re_content=#{re_content},re_ip=#{re_ip} WHERE re_num=#{re_num}")
	public void updateReview(ReviewCommand reviewCommand);
	//후기 삭제
	@Delete("DELETE FROM accom_review WHERE re_num=#{re_num}")
	public void deleteReview(Integer re_num);
	//별점
	@Insert("INSERT INTO accom_grade (ag_num,ag_email,ag_grade,ag_acc_num) VALUES (accom_grade_seq.nextval,#{re_email},#{ag_grade},#{re_acc_num})")
	public void insertStarGrade(Map<String,Object> map);
	@Select("SELECT * FROM accom_grade WHERE ag_email=#{re_email} AND ag_acc_num=#{re_acc_num}")
	public ReviewCommand selectStarGrade(Map<String,Object> map);
	@Update("UPDATE accom_grade SET ag_grade=#{ag_grade} WHERE ag_acc_num=#{re_acc_num} AND ag_email=#{re_email}")
	public void updateStarGrade(Map<String, Object> map);
	//좋아요
	@Insert("INSERT INTO accom_like (al_num,al_email,al_re_num,al_acc_num) VALUES (accom_like_seq.nextval,#{al_email},#{al_re_num},#{al_acc_num})")
	public void insertLike(Map<String,Object> map);
	@Select("SELECT * FROM accom_like WHERE al_email=#{al_email} AND al_re_num=#{al_re_num} AND al_acc_num=#{al_acc_num}")
	public ReviewCommand selectLikeList(Map<String,Object> map);
	@Delete("DELETE FROM accom_like WHERE al_re_num=#{al_re_num} AND al_acc_num=#{al_acc_num} AND al_email=#{al_email}")
	public void deleteLike(Map<String,Object> map);
	
	//=====프라이빗 하우스 페이지=====
	//프라이빗 하우스 이미지 가져오기
	@Select("SELECT im_cover,im_cover_name,im_image2,im_image2_name,im_image3,im_image3_name FROM accom_image WHERE im_ac_num=#{im_ac_num}")
	public PrivateDetailCommand selectPrivateImage(Integer im_ac_num);
	//프라이빗 하우스 상세
	public PrivateDetailCommand selectPrivateDetail(Integer im_ac_num);
	//예약되어있는지 체크하고 예약버튼 disable
	public Integer selectPrivateDisableCount(Map<String,Object> map);
	
	//평점이 높은 다른 숙소
	//검색한 날짜에 예약이 되어 있는지 조건 체크->상세 보여주지 않는 조건
	//호텔
	//호텔 목록(하위)
	public List<HotelDetailCommand> selectHotelRecommendationList(Map<String,Object> map);
	//프라이빗 하우스 목록(하위)
	public List<PrivateDetailCommand> selectPrivateRecommendationList(Map<String,Object> map);
	//검색한 날짜에 방이 예약이 되어있으면 예약불가능 처리
	public HotelDetailCommand selectHotelRvChecked(Map<String,Object> map);
	
	//datepicker
	//시작일과 종료일
	@Select("SELECT TO_CHAR(SYSDATE,'YYYY/MM/DD') start_date, TO_CHAR((ADD_MONTHS(SYSDATE,3)),'YYYY/MM/DD') end_date FROM dual")
	public PrivateDetailCommand selectStartEndDate();
	//제거일1
	@Select("SELECT rv_start_date FROM reservation WHERE TO_DATE((rv_start_date),'YYYY/MM/DD') > SYSDATE AND TO_DATE((rv_end_date),'YYYY/MM/DD') < ADD_MONTHS(SYSDATE,3) AND acc_num=#{im_ac_num}")
	public List<String> selectRvDateStart(Integer im_ac_num);
	@Select("SELECT rv_end_date FROM reservation WHERE TO_DATE((rv_start_date),'YYYY/MM/DD') > SYSDATE AND TO_DATE((rv_end_date),'YYYY/MM/DD') < ADD_MONTHS(SYSDATE,3) AND acc_num=#{im_ac_num}")
	public List<String> selectRvDateEnd(Integer im_ac_num);
	
	//리뷰 작성 후 리뷰 5개, 예약 내역 5개 이상이면 슈퍼호스트 업데이트
	public void updateSuperHost(Map<String,Object> map);
	
	//문의하기 insert(프라이빗 하우스)
	@Insert("INSERT INTO message (message_num,message_receiver,message_sender,message_title,message_reg_date,message_receive_status,message_send_status,message_content,message_url,message_type) VALUES (message_seq.nextval,#{message_receiver},#{message_sender},#{message_title},SYSDATE,-1,1,#{message_content},0,0)")
	public void insertHostMessageAccomDetail(Map<String,Object> map);
}
