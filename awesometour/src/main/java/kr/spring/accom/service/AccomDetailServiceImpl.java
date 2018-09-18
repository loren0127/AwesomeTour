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
	private AccomDetailMapper accomDetailMapper;
	
	//이미지 등록(나중에 제거)
	public void insertImage(ImageCommand image) {
		accomDetailMapper.insertImage(image);
	}

	@Override
	public List<HotelDetailCommand> selectHotelTotalImage(Integer im_ac_num) {
		return accomDetailMapper.selectHotelTotalImage(im_ac_num);
	}

	@Override
	public HotelDetailCommand selectHotelDetail(Integer im_ac_num) {
		return accomDetailMapper.selectHotelDetail(im_ac_num);
	}

	@Override
	public HotelDetailCommand selectHotelImage(Map<String,Object> map) {
		return accomDetailMapper.selectHotelImage(map);
	}

	@Override
	public String selectService(Integer im_ac_num) {
		return accomDetailMapper.selectService(im_ac_num);
	}

	@Override
	public Integer selectStandardRoomCount(Map<String, Object> map) {
		return accomDetailMapper.selectStandardRoomCount(map);
	}

	@Override
	public List<HotelDetailCommand> selectStandardRoom(Map<String, Object> map) {
		return accomDetailMapper.selectStandardRoom(map);
	}

	@Override
	public Integer selectDeluxRoomCount(Map<String, Object> map) {
		return accomDetailMapper.selectDeluxRoomCount(map);
	}

	@Override
	public List<HotelDetailCommand> selectDeluxRoom(Map<String, Object> map) {
		return accomDetailMapper.selectDeluxRoom(map);
	}

	@Override
	public Integer selectSuiteRoomCount(Map<String, Object> map) {
		return accomDetailMapper.selectSuiteRoomCount(map);
	}

	@Override
	public List<HotelDetailCommand> selectSuiteRoom(Map<String, Object> map) {
		return accomDetailMapper.selectSuiteRoom(map);
	}
	
	//후기
	@Override
	public List<ReviewCommand> selectListReview(Map<String, Object> map) {
		return accomDetailMapper.selectListReview(map);
	}

	@Override
	public int selectReviewCount(Map<String, Object> map) {
		return accomDetailMapper.selectReviewCount(map);
	}

	@Override
	public void insertReview(Map<String,Object> map) {
		accomDetailMapper.insertReview(map);
		accomDetailMapper.updateSuperHost(map);
	}

	@Override
	public void updateReview(ReviewCommand reviewCommand) {
		accomDetailMapper.updateReview(reviewCommand);
	}

	@Override
	public void deleteReview(Integer re_num) {
		accomDetailMapper.deleteReview(re_num);
	}

	@Override
	public void updateStarGrade(Map<String, Object> map) {
		accomDetailMapper.updateStarGrade(map);
	}

	@Override
	public void insertStarGrade(Map<String, Object> map) {
		accomDetailMapper.insertStarGrade(map);
	}

	@Override
	public ReviewCommand selectStarGrade(Map<String, Object> map) {
		return accomDetailMapper.selectStarGrade(map);
	}

	@Override
	public void insertLike(Map<String, Object> map) {
		accomDetailMapper.insertLike(map);
	}

	@Override
	public ReviewCommand selectLikeList(Map<String, Object> map) {
		return accomDetailMapper.selectLikeList(map);
	}

	@Override
	public void deleteLike(Map<String, Object> map) {
		accomDetailMapper.deleteLike(map);
	}

	@Override
	public PrivateDetailCommand selectPrivateImage(Integer im_ac_num) {
		return accomDetailMapper.selectPrivateImage(im_ac_num);
	}

	@Override
	public PrivateDetailCommand selectPrivateDetail(Integer im_ac_num) {
		return accomDetailMapper.selectPrivateDetail(im_ac_num);
	}

	@Override
	public HotelDetailCommand selectRvReivew(Map<String, Object> map) {
		return accomDetailMapper.selectRvReivew(map);
	}

	@Override
	public PrivateDetailCommand selectStartEndDate() {
		return accomDetailMapper.selectStartEndDate();
	}

	@Override
	public List<String> selectRvDateStart(Integer im_ac_num) {
		return accomDetailMapper.selectRvDateStart(im_ac_num);
	}

	@Override
	public List<String> selectRvDateEnd(Integer im_ac_num) {
		return accomDetailMapper.selectRvDateEnd(im_ac_num);
	}

	@Override
	public List<HotelDetailCommand> selectHotelRecommendationList(Map<String,Object> map) {
		return accomDetailMapper.selectHotelRecommendationList(map);
	}

	@Override
	public List<PrivateDetailCommand> selectPrivateRecommendationList(Map<String,Object> map) {
		return accomDetailMapper.selectPrivateRecommendationList(map);
	}

	@Override
	public HotelDetailCommand selectHotelRvChecked(Map<String, Object> map) {
		return accomDetailMapper.selectHotelRvChecked(map);
	}

	@Override
	public List<ReviewCommand> selectListReview2(Map<String, Object> map) {
		return accomDetailMapper.selectListReview2(map);
	}

	@Override
	public Integer selectPrivateDisableCount(Map<String, Object> map) {
		return accomDetailMapper.selectPrivateDisableCount(map);
	}

	@Override
	public void insertHostMessageAccomDetail(Map<String, Object> map) {
		accomDetailMapper.insertHostMessageAccomDetail(map);
	}

	@Override
	public void updateSuperHost(Map<String, Object> map) {
		// TODO Auto-generated method stub
		accomDetailMapper.updateSuperHost(map);

	}
}
