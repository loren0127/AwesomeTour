package kr.spring.accom.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.accom.dao.AccomDetailMapper;
import kr.spring.accom.domain.HotelDetailCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.domain.PrivateDetailCommand;
import kr.spring.accom.domain.ReviewCommand;

@Service("accomDetailService")
public class AccomDetailServiceImpl implements AccomDetailService{
	
	@Resource
	private AccomDetailMapper accomMapper;
	
	//이미지 등록(나중에 제거)
	public void insertImage(ImageCommand image) {
		accomMapper.insertImage(image);
	}

	@Override
	public List<HotelDetailCommand> selectHotelTotalImage(Integer im_ac_num) {
		return accomMapper.selectHotelTotalImage(im_ac_num);
	}

	@Override
	public HotelDetailCommand selectHotelDetail(Integer im_ac_num) {
		return accomMapper.selectHotelDetail(im_ac_num);
	}

	@Override
	public HotelDetailCommand selectHotelImage(Map<String,Object> map) {
		return accomMapper.selectHotelImage(map);
	}

	@Override
	public String selectService(Integer im_ac_num) {
		return accomMapper.selectService(im_ac_num);
	}

	@Override
	public Integer selectStandardRoomCount(Map<String, Object> map) {
		return accomMapper.selectStandardRoomCount(map);
	}

	@Override
	public List<HotelDetailCommand> selectStandardRoom(Map<String, Object> map) {
		return accomMapper.selectStandardRoom(map);
	}

	@Override
	public Integer selectDeluxRoomCount(Map<String, Object> map) {
		return accomMapper.selectDeluxRoomCount(map);
	}

	@Override
	public List<HotelDetailCommand> selectDeluxRoom(Map<String, Object> map) {
		return accomMapper.selectDeluxRoom(map);
	}

	@Override
	public Integer selectSuiteRoomCount(Map<String, Object> map) {
		return accomMapper.selectSuiteRoomCount(map);
	}

	@Override
	public List<HotelDetailCommand> selectSuiteRoom(Map<String, Object> map) {
		return accomMapper.selectSuiteRoom(map);
	}
	
	//후기
	@Override
	public List<ReviewCommand> selectListReview(Map<String, Object> map) {
		return accomMapper.selectListReview(map);
	}

	@Override
	public int selectReviewCount(Map<String, Object> map) {
		return accomMapper.selectReviewCount(map);
	}

	@Override
	public void insertReview(Map<String,Object> map) {
		accomMapper.insertReview(map);
		accomMapper.updateSuperHost(map);
	}

	@Override
	public void updateReview(ReviewCommand reviewCommand) {
		accomMapper.updateReview(reviewCommand);
	}

	@Override
	public void deleteReview(Integer re_num) {
		accomMapper.deleteReview(re_num);
	}

	@Override
	public void updateStarGrade(Map<String, Object> map) {
		accomMapper.updateStarGrade(map);
	}

	@Override
	public void insertStarGrade(Map<String, Object> map) {
		accomMapper.insertStarGrade(map);
	}

	@Override
	public ReviewCommand selectStarGrade(Map<String, Object> map) {
		return accomMapper.selectStarGrade(map);
	}

	@Override
	public void insertLike(Map<String, Object> map) {
		accomMapper.insertLike(map);
	}

	@Override
	public ReviewCommand selectLikeList(Map<String, Object> map) {
		return accomMapper.selectLikeList(map);
	}

	@Override
	public void deleteLike(Map<String, Object> map) {
		accomMapper.deleteLike(map);
	}

	@Override
	public PrivateDetailCommand selectPrivateImage(Integer im_ac_num) {
		return accomMapper.selectPrivateImage(im_ac_num);
	}

	@Override
	public PrivateDetailCommand selectPrivateDetail(Integer im_ac_num) {
		return accomMapper.selectPrivateDetail(im_ac_num);
	}

	@Override
	public HotelDetailCommand selectRvReivew(Map<String, Object> map) {
		return accomMapper.selectRvReivew(map);
	}

	@Override
	public PrivateDetailCommand selectStartEndDate() {
		return accomMapper.selectStartEndDate();
	}

	@Override
	public List<String> selectRvDateStart(Integer im_ac_num) {
		return accomMapper.selectRvDateStart(im_ac_num);
	}

	@Override
	public List<String> selectRvDateEnd(Integer im_ac_num) {
		return accomMapper.selectRvDateEnd(im_ac_num);
	}

	@Override
	public List<HotelDetailCommand> selectHotelRecommendationList(Map<String,Object> map) {
		return accomMapper.selectHotelRecommendationList(map);
	}

	@Override
	public List<PrivateDetailCommand> selectPrivateRecommendationList(Map<String,Object> map) {
		return accomMapper.selectPrivateRecommendationList(map);
	}

	@Override
	public HotelDetailCommand selectHotelRvChecked(Map<String, Object> map) {
		return accomMapper.selectHotelRvChecked(map);
	}

	@Override
	public List<ReviewCommand> selectListReview2(Map<String, Object> map) {
		return accomMapper.selectListReview2(map);
	}
}
