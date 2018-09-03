package kr.spring.accom.service;

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

public interface AccomDetailService {
	//이미지 등록(나중에 제거)
	public void insertImage(ImageCommand image);
	//=====호텔 상세 페이지======
	//호텔 전체 이미지 가져오기
	public List<HotelDetailCommand> selectHotelTotalImage(Integer im_ac_num);
	public HotelDetailCommand selectHotelImage(Map<String,Object> map);
	//상세페이지 상단
	public HotelDetailCommand selectHotelDetail(Integer im_ac_num);
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
	public List<ReviewCommand> selectListReview(Map<String, Object> map);
	//리스트 카운트
	public int selectReviewCount(Map<String, Object> map);
	//후기 작성시 해당 숙소를 현재 날짜보다 이전에 예약한 사람만 작성가능
	public HotelDetailCommand selectRvReivew(Map<String,Object> map);
	//후기 등록
	public void insertReview(Map<String,Object> map);
	//후기 수정
	public void updateReview(ReviewCommand reviewCommand);
	//후기 삭제
	public void deleteReview(Integer re_num);
	//별점
	public void insertStarGrade(Map<String,Object> map);
	public ReviewCommand selectStarGrade(Map<String,Object> map);
	public void updateStarGrade(Map<String, Object> map);
	//좋아요
	public void insertLike(Map<String,Object> map);
	public ReviewCommand selectLikeList(Map<String,Object> map);
	public void deleteLike(Map<String,Object> map);

	//=====프라이빗 하우스 페이지=====
	//프라이빗 하우스 이미지 가져오기
	public PrivateDetailCommand selectPrivateImage(Integer im_ac_num);
	//프라이빗 하우스 상세
	public PrivateDetailCommand selectPrivateDetail(Integer im_ac_num);

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
	public PrivateDetailCommand selectStartEndDate();
	//제거일1
	public List<String> selectRvDateStart();
	public List<String> selectRvDateEnd();
}
